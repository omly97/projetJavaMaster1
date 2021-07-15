package model.entites;

public class OperateurStat extends Operateur {

	private int montantPaye;
	private int montantDette;
	
	public OperateurStat() {
		// TODO Auto-generated constructor stub
	}


	public int getMontantPaye() {
		return montantPaye;
	}
	
	public void setMontantPaye(int montantPaye) {
		this.montantPaye = montantPaye;
	}
	
	public int getMontantDette() {
		return montantDette;
	}
	
	public void setMontantDette(int montantDette) {
		this.montantDette = montantDette;
	}
}
