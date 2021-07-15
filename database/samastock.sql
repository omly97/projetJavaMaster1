-- -----------------------------------------------------
-- Schema DB_SAMASTOCK
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DB_SAMASTOCK` DEFAULT CHARACTER SET utf8 ;
USE `DB_SAMASTOCK` ;


-- -----------------------------------------------------
-- Table `DB_SAMASTOCK`.`produits`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DB_SAMASTOCK`.`produits`;

CREATE TABLE IF NOT EXISTS `DB_SAMASTOCK`.`produits` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nom` VARCHAR(45) NOT NULL,
    `categorie` VARCHAR(45) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `nom_UNIQUE` ON `DB_SAMASTOCK`.`produits` (`nom` ASC);


-- -----------------------------------------------------
-- Table `DB_SAMASTOCK`.`operateurs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DB_SAMASTOCK`.`operateurs`;

CREATE TABLE IF NOT EXISTS `DB_SAMASTOCK`.`operateurs` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nom` VARCHAR(45) NOT NULL,
    `adresse` VARCHAR(45) NULL,
    `telephone` VARCHAR(45) NOT NULL,
    `type` VARCHAR(45) NOT NULL,
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `telephone_UNIQUE` ON `DB_SAMASTOCK`.`operateurs` (`telephone` ASC);


-- -----------------------------------------------------
-- Table `DB_SAMASTOCK`.`operations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DB_SAMASTOCK`.`operations`;

CREATE TABLE IF NOT EXISTS `DB_SAMASTOCK`.`operations` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nature` ENUM('APPROVISION', 'VENTE') NOT NULL,
    `type` ENUM('ACHAT', 'PRET') NOT NULL,
    `quantite` INT UNSIGNED NOT NULL,
    `prix_unitaire` INT UNSIGNED NOT NULL,
    `montant` INT UNSIGNED GENERATED ALWAYS AS (`quantite` * `prix_unitaire`),
    `paiement` TINYINT(1) NOT NULL DEFAULT 0,
    `date_operation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `operateur_id` BIGINT NOT NULL,
    `produit_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_operations_operateur_id`
        FOREIGN KEY (`operateur_id`)
        REFERENCES `DB_SAMASTOCK`.`operateurs` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT `fk_operations_produit_id`
        FOREIGN KEY (`produit_id`)
        REFERENCES `DB_SAMASTOCK`.`produits` (`id`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
)
ENGINE = InnoDB;

CREATE INDEX `fk_operations_operateurs1_idx` ON `DB_SAMASTOCK`.`operations` (`operateur_id` ASC);

CREATE INDEX `fk_operations_produits1_idx` ON `DB_SAMASTOCK`.`operations` (`produit_id` ASC);


-- -----------------------------------------------------
-- Table `DB_SAMASTOCK`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DB_SAMASTOCK`.`users`;

CREATE TABLE IF NOT EXISTS `DB_SAMASTOCK`.`users` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `nom` VARCHAR(45) NOT NULL,
    `role` ENUM('ADMIN', 'APPROVISION', 'VENTE') NOT NULL,
    `login` VARCHAR(80) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `login_UNIQUE` ON `DB_SAMASTOCK`.`users` (`login` ASC);


-- -----------------------------------------------------
-- Table `DB_SAMASTOCK`.`logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `DB_SAMASTOCK`.`logs`;

CREATE TABLE IF NOT EXISTS `DB_SAMASTOCK`.`logs` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `table` ENUM('produits', 'operateurs', 'operations') NOT NULL,
    `ligne` BIGINT NOT NULL COMMENT 'ID des lignes',
    `action` ENUM('create', 'update', 'delete') NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `user_id` BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_logs_user1`
        FOREIGN KEY (`user_id`)
        REFERENCES `DB_SAMASTOCK`.`users` (`id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
)
ENGINE = InnoDB;

CREATE INDEX `fk_logs_user1_idx` ON `DB_SAMASTOCK`.`logs` (`user_id` ASC);

CREATE UNIQUE INDEX `created_at_UNIQUE` ON `DB_SAMASTOCK`.`logs` (`created_at` ASC);


-- -----------------------------------------------------
-- View `DB_SAMASTOCK`.`view_operations`
-- -----------------------------------------------------
CREATE  OR REPLACE VIEW `view_operations` AS
SELECT
    `operations`.`id`,
	`nature`,
    `operations`.`type`,
    `quantite`,
    `prix_unitaire`,
    `montant`,
    `paiement`,
    `date_operation`,
	`produit_id` AS `p_id`,
    `produits`.`nom` AS `p_nom`,
    `produits`.`categorie` AS `p_categorie`,
    `operateur_id` AS `op_id`,
    `operateurs`.`nom` AS `op_nom`,
    `operateurs`.`adresse` AS `op_adresse`,
    `operateurs`.`adresse` AS `op_telephone`,
    `operateurs`.`type` AS `op_type`
FROM `operations`
INNER JOIN `produits` ON `produits`.`id` = `produit_id`
INNER JOIN `operateurs` ON `operateurs`.`id` = `operateur_id`;

-- -----------------------------------------------------
-- View `DB_SAMASTOCK`.`view_stock_produits`
-- -----------------------------------------------------
CREATE  OR REPLACE VIEW `view_stock_produits` AS
SELECT
	`id`,
    `nom`,
    `categorie`,
    `qte_approvision`,
    `qte_vente`,
    `qte_approvision` - `qte_vente` AS `qte_stock`
FROM `produits`
LEFT JOIN
	(
		SELECT `produit_id`, SUM(`quantite`) AS `qte_approvision`
		FROM `operations`
		WHERE `nature` = 'APPROVISION'
		GROUP BY `produit_id`
    ) AS `count_approvision` ON `id` = `count_approvision`.`produit_id`
LEFT JOIN
	(
		SELECT `produit_id`, SUM(`quantite`) AS `qte_vente`
		FROM `operations`
		WHERE `nature` = 'VENTE'
		GROUP BY `produit_id`
    ) AS `count_ventes` ON `id` = `count_ventes`.`produit_id`
ORDER BY `nom`;

-- -----------------------------------------------------
-- View `DB_SAMASTOCK`.`view_finance_produits`
-- -----------------------------------------------------
CREATE  OR REPLACE VIEW `view_finance_produits` AS
SELECT
	`id`,
    `nom`,
    `categorie`,
    `montant_achat`,
    `montant_emprunt`,
    `montant_vente`,
    `montant_pret`
FROM `produits`
LEFT JOIN
	(
		SELECT `produit_id`, SUM(`montant`) AS `montant_achat`
		FROM `operations`
		WHERE `nature` = 'APPROVISION'
			AND (`type` = 'ACHAT' OR (`type` = 'PRET' AND `paiement` IS TRUE))
		GROUP BY `produit_id`
    )
    AS `view_achats` ON `id` = `view_achats`.`produit_id`
LEFT JOIN
	(
		SELECT `produit_id`, SUM(`montant`) AS `montant_emprunt`
		FROM `operations`
		WHERE `nature` = 'APPROVISION' AND `type` = 'PRET' AND `paiement` IS FALSE
		GROUP BY `produit_id`
    )
    AS `view_emprunts` ON `id` = `view_emprunts`.`produit_id`
LEFT JOIN
	(
		SELECT `produit_id`, SUM(`montant`) AS `montant_vente`
		FROM `operations`
		WHERE `nature` = 'VENTE'
		AND (`type` = 'ACHAT' OR (`type` = 'PRET' AND `paiement` IS TRUE))
		GROUP BY `produit_id`
    )
    AS `view_ventes` ON `id` = `view_ventes`.`produit_id`
LEFT JOIN
	(
		SELECT `produit_id`, SUM(`montant`) AS `montant_pret`
		FROM `operations`
		WHERE `nature` = 'VENTE' AND `type` = 'PRET' AND `paiement` IS FALSE
		GROUP BY `produit_id`
    )
    AS `view_prets` ON `id` = `view_prets`.`produit_id`
ORDER BY `nom`;

-- -----------------------------------------------------
-- View `DB_SAMASTOCK`.`view_produits`
-- -----------------------------------------------------
CREATE  OR REPLACE VIEW `view_produits` AS
SELECT
	`produits`.`id`,
    `produits`.`nom`,
    `produits`.`categorie`,
	`qte_approvision`,
    `qte_vente`,
    `qte_stock`,
    `montant_achat`,
    `montant_emprunt`,
    `montant_vente`,
    `montant_pret`
FROM `produits`
LEFT JOIN `view_stock_produits` ON `produits`.`id` = `view_stock_produits`.`id`
LEFT JOIN `view_finance_produits` ON `produits`.`id` = `view_finance_produits`.`id`;

-- -----------------------------------------------------
-- View `DB_SAMASTOCK`.`view_finance_operateurs`
-- -----------------------------------------------------
CREATE  OR REPLACE VIEW `view_finance_operateurs` AS
SELECT
	`id`,
	`nom`,
    `adresse`,
    `telephone`,
    `type`,
    `montant_paye`,
    `montant_dette`
FROM `operateurs`
LEFT JOIN
	(
		SELECT `operateur_id`, SUM(`montant`) AS `montant_paye`
		FROM `operations`
		WHERE `paiement` IS TRUE
		GROUP BY `operateur_id`
    )
    AS `view_paie` ON `id` = `view_paie`.`operateur_id`
LEFT JOIN
	(
		SELECT `operateur_id`, SUM(`montant`) AS `montant_dette`
		FROM `operations`
		WHERE `paiement` IS FALSE
		GROUP BY `operateur_id`
    )
    AS `view_dette` ON `id` = `view_dette`.`operateur_id`;


-- ------------------- DELIMITER
DELIMITER $$


-- -----------------------------------------------------
-- Procedure `DB_SAMASTOCK`.`controle_nature_operation`
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `DB_SAMASTOCK`.`controle_nature_operation`;

CREATE PROCEDURE `controle_nature_operation`(`p_nature` VARCHAR(45), `p_operateur_id` BIGINT)
BEGIN
	DECLARE `v_type` VARCHAR(25);

    SELECT `type`
    FROM `operateurs`
    WHERE `id` = `p_operateur_id`
    INTO `v_type`;
    
    IF `p_nature` = 'APPROVISION' AND `v_type` = 'CLIENT' THEN
		SIGNAL SQLSTATE '49000'
			SET MESSAGE_TEXT = 'Approvision chez client interdit';
    END IF;
    
    IF `p_nature` = 'VENTE' AND `v_type` = 'FOURNISSEUR' THEN
		SIGNAL SQLSTATE '49000'
			SET MESSAGE_TEXT = 'Vente au fournisseur interdit';
    END IF;
END$$


-- -----------------------------------------------------
-- Procedure `DB_SAMASTOCK`.`control_operation_vente`
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS `DB_SAMASTOCK`.`control_operation_vente`;

CREATE PROCEDURE `control_operation_vente`(`p_produit_id` BIGINT, `p_quantite` INT)
BEGIN
	DECLARE `qte_achetee` INT;
    DECLARE `qte_vendue` INT;
    DECLARE `qte_stock` INT;
    
    SELECT COALESCE(SUM(`quantite`), 0)
    FROM `operations`
    WHERE `produit_id` = `p_produit_id` AND `nature` = 'APPROVISION'
    INTO `qte_achetee`;
    
    SELECT COALESCE(SUM(`quantite`), 0)
    FROM `operations`
    WHERE `produit_id` = `p_produit_id` AND `nature` = 'VENTE'
    INTO `qte_vendue`;
    
    SET `qte_stock` = `qte_achetee` - `qte_vendue`;
    
    IF `qte_stock` < `p_quantite` THEN
		SIGNAL SQLSTATE '49000'
			SET MESSAGE_TEXT = 'Stock insuffisante';
    END IF;
END$$


-- -----------------------------------------------------
-- Trigger `DB_SAMASTOCK`.`operations_BEFORE_INSERT`
-- -----------------------------------------------------
DROP TRIGGER IF EXISTS `DB_SAMASTOCK`.`operations_BEFORE_INSERT`;

CREATE TRIGGER `DB_SAMASTOCK`.`operations_BEFORE_INSERT` BEFORE INSERT ON `operations` FOR EACH ROW
BEGIN
	IF NEW.`type` = 'ACHAT' THEN
		SET NEW.`paiement` = TRUE;
	END IF;

    CALL `controle_nature_operation`(NEW.`nature`, NEW.`operateur_id`);

	IF NEW.`nature` = 'VENTE' THEN
		CALL `control_operation_vente`(NEW.`produit_id`, NEW.`quantite`);
	END IF;
END$$


-- -----------------------------------------------------
-- Trigger `DB_SAMASTOCK`.`operations_BEFORE_UPDATE`
-- -----------------------------------------------------
DROP TRIGGER IF EXISTS `DB_SAMASTOCK`.`operations_BEFORE_UPDATE`;

CREATE TRIGGER `DB_SAMASTOCK`.`operations_BEFORE_UPDATE` BEFORE UPDATE ON `operations` FOR EACH ROW
BEGIN
	IF NEW.`type` = 'ACHAT' THEN
		SET NEW.`paiement` = TRUE;
	END IF;
    
    CALL `controle_nature_operation`(NEW.`nature`, NEW.`operateur_id`);
    
    IF NEW.`nature` = 'VENTE' THEN
		IF NEW.`produit_id` = OLD.`produit_id` THEN
			IF NEW.`quantite` - OLD.`quantite` > 0 THEN
				CALL `control_operation_vente`(NEW.`produit_id`, NEW.`quantite` - OLD.`quantite`);
			END IF;
		ELSE
			CALL `control_operation_vente`(NEW.`produit_id`, NEW.`quantite`);
		END IF;
    END IF;
END$$


-- ------------------- DELIMITER
DELIMITER ;
