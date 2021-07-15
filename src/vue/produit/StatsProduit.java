package vue.produit;

import java.awt.GridLayout;

import javax.swing.JLabel;

import model.entites.ProduitStat;
import myUX.MyPanel;
import vue.component.Card;

@SuppressWarnings("serial")
public class StatsProduit extends MyPanel {

	private Card cardQteApprovision;
	private Card cardQteVente;
	private Card cardQteStock;
	private Card cardDepenseApprovision;
	private Card cardDetteApprovision;
	private Card cardRevenuVente;
	private Card cardDetteVente;
	
	
	public StatsProduit() {
		super();
		cardQteApprovision = new Card();
		cardQteVente = new Card();
		cardQteStock = new Card();
		cardDepenseApprovision = new Card();
		cardDetteApprovision = new Card();
		cardRevenuVente = new Card();
		cardDetteVente = new Card();
		setLayout(new GridLayout(2, 4, 5, 5));
		add(cardQteApprovision);
		add(cardQteVente);
		add(cardQteStock);
		add(new JLabel());
		add(cardDepenseApprovision);
		add(cardDetteApprovision);
		add(cardRevenuVente);
		add(cardDetteVente);
	}

	public void setProduitStat(ProduitStat p) {
		cardQteApprovision.setTitre(p.getQuantiteApprovision());
		cardQteApprovision.setTexte("Qte. Approvision");
		
		cardQteVente.setTitre(p.getQuantiteVente());
		cardQteVente.setTexte("Qte. Vente");
		
		cardQteStock.setTitre(p.getQuantiteStock());
		cardQteStock.setTexte("Qte. Stock");
		
		cardDepenseApprovision.setTitre(p.getMontantAchat(), "f");
		cardDepenseApprovision.setTexte("Montant Achat");
		
		cardDetteApprovision.setTitre(p.getMontantEmprunt(), "f");
		cardDetteApprovision.setTexte("Montant Emprunt");
		
		cardRevenuVente.setTitre(p.getMontantVente(), "f");
		cardRevenuVente.setTexte("Montant Vente");
		
		cardDetteVente.setTitre(p.getMontantPret(), "f");
		cardDetteVente.setTexte("Montant Prêt");
	}
}
