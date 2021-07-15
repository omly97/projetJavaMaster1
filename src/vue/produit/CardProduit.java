package vue.produit;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import img.MyIcon;
import model.entites.Produit;
import myUX.MyLabel;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class CardProduit extends MyPanel {

	private MyPanel panelNord;
	private MyPanel panelCentre;
	private JLabel lTitreNom;
	private JLabel lTitreCategorie;
	private JLabel lNom;
	private JLabel lCategorie;
	
	
	public CardProduit(boolean showTitle) {
		super();
		if (showTitle) {
			// PANEL NORD
			panelNord = new MyPanel();
			panelNord.setLayout(new BorderLayout());
			panelNord.add(new JLabel(MyIcon.createImageIcon(90, 90, "img01-produit.png")), BorderLayout.CENTER);

			// PANEL SUD
			panelCentre = new MyPanel();
			lTitreNom = new MyLabel("Nom", MyLabel.H6);
			lTitreCategorie = new MyLabel("Categorie", MyLabel.H6);
			lNom = new MyLabel(MyLabel.H5);
			lCategorie = new MyLabel(MyLabel.H5);
			panelCentre.setLayout(new GridLayout(2, 2, 0, 5));
			panelCentre.add(lTitreNom);
			panelCentre.add(lNom);
			panelCentre.add(lTitreCategorie);
			panelCentre.add(lCategorie);

			// THIS PANEL
			setLayout(new BorderLayout());
			add(panelNord, BorderLayout.NORTH);
			add(panelCentre, BorderLayout.CENTER);
			setBorder(new EmptyBorder(20, 20, 20, 20));
		}
		else {
			lNom = new MyLabel(MyLabel.H4);
			lCategorie = new MyLabel(MyLabel.H5);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(lNom);
			add(lCategorie);
		}
	}

	public void setProduit(Produit p) {
		lNom.setText(p.getNom());
		lCategorie.setText(p.getCategorie());
	}
}
