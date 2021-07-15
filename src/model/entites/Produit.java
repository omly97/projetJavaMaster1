package model.entites;

public class Produit {

	protected Long id;
	protected String nom;
	protected String categorie;

	
	public Produit() {
		// TODO Auto-generated constructor stub
	}

	public Produit(String nom, String categorie) {
		this.nom = nom;
		this.categorie = categorie;
	}
	
	public Produit(Long id, String nom, String categorie) {
		this(nom, categorie);
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
}
