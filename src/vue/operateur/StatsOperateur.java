package vue.operateur;

import java.awt.GridLayout;

import model.entites.OperateurStat;
import myUX.MyPanel;
import vue.component.Card;

@SuppressWarnings("serial")
public class StatsOperateur extends MyPanel {
	
	private Card cardMontantTotal;
	private Card cardMontantPaye;
	private Card cardMontantDette;

	public StatsOperateur() {
		super();
		cardMontantTotal = new Card();
		cardMontantPaye = new Card();
		cardMontantDette = new Card();
		setLayout(new GridLayout(1, 3, 5, 5));
		add(cardMontantTotal);
		add(cardMontantPaye);
		add(cardMontantDette);
	}
	
	public void setOperateurStat(OperateurStat op) {
		cardMontantTotal.setTitre(op.getMontantPaye() + op.getMontantDette(), "f");
		cardMontantTotal.setTexte("Montant total");
		
		cardMontantPaye.setTitre(op.getMontantPaye(), "f");
		cardMontantPaye.setTexte("Montant payé");
		
		cardMontantDette.setTitre(op.getMontantDette(), "f");
		cardMontantDette.setTexte("Montant dette");
	}
}
