package model.entites;

public class Operateur {

	protected Long id;
	protected String nom;
	protected String adresse;
	protected String telephone;
	protected String type;
	
	public static final String TYPE_CLIENT = "CLIENT";
	public static final String TYPE_FOURNISSEUR = "FOURNISSEUR";

	
	public Operateur() {
		// TODO Auto-generated constructor stub
	}
	
	public Operateur(String nom, String adresse, String telephone, String type) {
		this.nom = nom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.type = type;
	}

	public Operateur(Long id, String nom, String adresse, String telephone, String type) {
		this(nom, adresse, telephone, type);
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
