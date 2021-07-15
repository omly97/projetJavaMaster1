package model.entites;

import java.sql.Date;

public class Operation {

	private Long id;
	private String nature;
	private String type;
	private int quantite;
	private int prixUnitaire;
	private int montant;
	private boolean paiement;
	private Date dateOperation;
	private Operateur operateur;
	private Produit produit;
	
	public static final String NATURE_APPROVISION = "APPROVISION";
	public static final String NATURE_VENTE = "VENTE";

	public static final String[] TYPES = {"ACHAT", "PRET"};

	
	public Operation() {
		// TODO Auto-generated constructor stub
	}
	
	public Operation(String nature, String type, int quantite, int prixUnitaire, boolean paiement, Date dateOperation) {
		this.nature = nature;
		this.type = type;
		this.quantite = quantite;
		this.prixUnitaire = prixUnitaire;
		this.paiement = paiement;
		this.dateOperation = dateOperation;
	}

	public Operation(Long id, String nature, String type, int quantite, int prixUnitaire, boolean paiement,
			Date dateOperation) {
		this(nature, type, quantite, prixUnitaire, paiement, dateOperation);
		this.id = id;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(int prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	
	public int getMontant() {
		return montant;
	}
	
	public void setMontant(int montant) {
		this.montant = montant;
	}

	public boolean isPaiement() {
		return paiement;
	}

	public void setPaiement(boolean paiement) {
		this.paiement = paiement;
	}

	public Date getDateOperation() {
		return dateOperation;
	}

	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	public Operateur getOperateur() {
		return operateur;
	}

	public void setOperateur(Operateur operateur) {
		this.operateur = operateur;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	public String getEtat() {
		return paiement == true ? "PAYE" : "NON PAYE";
	}
}
