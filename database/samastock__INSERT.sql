INSERT INTO `produits`(`nom`, `categorie`) VALUES
('Produit 01', 'Categorie 01'),
('Produit 02', 'Categorie 02'),
('Produit 03', 'Categorie 03'),
('Produit 04', 'Categorie 04'),
('Produit 05', 'Categorie 05');

INSERT INTO `operateurs`(`nom`, `adresse`, `telephone`, `type`) VALUES
('Fournisseur 01', 'Addr fournisseur 01', '33 330 30 01', 'FOURNISSEUR'),
('Fournisseur 02', 'Addr fournisseur 02', '33 330 30 02', 'FOURNISSEUR'),
('Fournisseur 03', 'Addr fournisseur 03', '33 330 30 03', 'FOURNISSEUR'),
('Client 01', 'Addr client 01', '77 700 70 01', 'CLIENT'),
('Client 02', 'Addr client 02', '77 700 70 02', 'CLIENT'),
('Client 03', 'Addr client 03', '77 700 70 03', 'CLIENT');

INSERT INTO `operations`(`nature`, `type`, `quantite`, `prix_unitaire`, `paiement`, `operateur_id`, `produit_id`) VALUES
('APPROVISION', 'PRET', 20, 15000, TRUE, 1, 1),
('APPROVISION', 'PRET', 40, 17000, TRUE, 1, 2),
('APPROVISION', 'PRET', 30, 20000, TRUE, 1, 3),

('APPROVISION', 'ACHAT', 125, 15000, TRUE, 2, 1),
('APPROVISION', 'ACHAT', 100, 17000, TRUE, 2, 2),
('APPROVISION', 'ACHAT', 150, 20000, TRUE, 2, 3),

('APPROVISION', 'PRET', 50, 15000, FALSE, 3, 1),
('APPROVISION', 'PRET', 80, 17000, FALSE, 3, 2),
('APPROVISION', 'PRET', 50, 20000, FALSE, 3, 3),

('VENTE', 'ACHAT', 10, 17000, TRUE, 4, 1),
('VENTE', 'PRET', 20, 19000, FALSE, 4, 2),
('VENTE', 'PRET', 20, 22000, TRUE, 4, 3),

('VENTE', 'ACHAT', 100, 15000, TRUE, 5, 1),
('VENTE', 'PRET', 80, 17000, TRUE, 5, 2),
('VENTE', 'PRET', 50, 20000, FALSE, 5, 3);

INSERT INTO `users`(`nom`, `role`, `login`, `password`) VALUES
('Admin', 'ADMIN', 'admin', SHA('Passe123')),
('User Appro', 'APPROVISION', 'user1', SHA('Passe123')),
('User Vente', 'VENTE', 'user2', SHA('Passe123'));
