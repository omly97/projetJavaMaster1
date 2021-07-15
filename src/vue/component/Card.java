package vue.component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import myUX.MyLabel;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class Card extends MyPanel {

	private JLabel lValeur;
	private JLabel lTexte;
	
	public Card() {
		super(true);
		lTexte = new MyLabel();
		lValeur = new MyLabel(MyLabel.H3);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(lTexte);
		add(lValeur);
		setBorder(new EmptyBorder(20, 20, 20, 20));
	}
	
	public void setTitre(int valeur) {
		lValeur.setText(String.valueOf(valeur));
	}
	
	public void setTitre(int valeur, String unite) {
		lValeur.setText(String.valueOf(valeur) + " " + unite);
	}
	
	public void setTexte(String texte) {
		lTexte.setText(texte);
	}
}
