package model.entites;

public class ProduitStat extends Produit {

	private int quantiteApprovision;
	private int quantiteVente;
	private int quantiteStock;
	private int montantAchat;
	private int montantEmprunt;
	private int montantVente;
	private int montantPret;

	
	public ProduitStat() {
		super();
	}
	
	public ProduitStat(Produit produit) {
		super(produit.nom, produit.categorie);
	}
	
	public int getQuantiteApprovision() {
		return quantiteApprovision;
	}
	
	public void setQuantiteApprovision(int quantiteApprovision) {
		this.quantiteApprovision = quantiteApprovision;
	}

	public int getQuantiteVente() {
		return quantiteVente;
	}

	public void setQuantiteVente(int quantiteVente) {
		this.quantiteVente = quantiteVente;
	}

	public int getQuantiteStock() {
		return quantiteStock;
	}

	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	
	public int getMontantAchat() {
		return montantAchat;
	}
	
	public void setMontantAchat(int montantAchat) {
		this.montantAchat = montantAchat;
	}
	
	public int getMontantEmprunt() {
		return montantEmprunt;
	}
	
	public void setMontantEmprunt(int montantEmprunt) {
		this.montantEmprunt = montantEmprunt;
	}
	
	public int getMontantVente() {
		return montantVente;
	}
	
	public void setMontantVente(int montantVente) {
		this.montantVente = montantVente;
	}
	
	public int getMontantPret() {
		return montantPret;
	}
	
	public void setMontantPret(int montantPret) {
		this.montantPret = montantPret;
	}
}
