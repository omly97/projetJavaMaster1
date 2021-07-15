package vue.operateur;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import img.MyIcon;
import model.entites.Operateur;
import myUX.MyLabel;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class CardOperateur extends MyPanel {

	private MyPanel panelNord;
	private MyPanel panelCentre;
	private MyLabel lTitreNom;
	private MyLabel lTitreAdresse;
	private MyLabel lTitreTelephone;
	private MyLabel lTitreType;
	private MyLabel lNom;
	private MyLabel lAdresse;
	private MyLabel lTelephone;
	private MyLabel lType;
	
	public CardOperateur(boolean showTitle) {
		super();
		if (showTitle) {
			// PANEL NORD
			panelNord = new MyPanel();
			panelNord.setLayout(new BorderLayout());
			panelNord.add(new JLabel(MyIcon.createImageIcon(90, 90, "img03-client.png")), BorderLayout.CENTER);

			// PANEL SUD
			panelCentre = new MyPanel();
			lTitreNom = new MyLabel("Nom", MyLabel.H6);
			lTitreAdresse = new MyLabel("Adresse", MyLabel.H6);
			lTitreTelephone = new MyLabel("Telephone", MyLabel.H6);
			lTitreType = new MyLabel("Type", MyLabel.H6);
			lNom = new MyLabel(MyLabel.H5);
			lAdresse = new MyLabel(MyLabel.H5);
			lTelephone = new MyLabel(MyLabel.H5);
			lType = new MyLabel(MyLabel.H5);
			panelCentre.setLayout(new GridLayout(4, 2, 0, 5));
			panelCentre.add(lTitreNom);
			panelCentre.add(lNom);
			panelCentre.add(lTitreType);
			panelCentre.add(lType);
			panelCentre.add(lTitreTelephone);
			panelCentre.add(lTelephone);
			panelCentre.add(lTitreAdresse);
			panelCentre.add(lAdresse);

			// THIS PANEL
			setLayout(new BorderLayout());
			add(panelNord, BorderLayout.NORTH);
			add(panelCentre, BorderLayout.CENTER);
			setBorder(new EmptyBorder(20, 20, 20, 20));
		}
		else {
			lNom = new MyLabel(MyLabel.H4);
			lAdresse = new MyLabel(MyLabel.H5);
			lTelephone = new MyLabel(MyLabel.H5);
			lType = new MyLabel(MyLabel.H5);
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(lNom);
			add(lType);
			add(lTelephone);
			add(lAdresse);
		}
	}

	public void setOperateur(Operateur op) {
		lNom.setText(op.getNom());
		lAdresse.setText(op.getAdresse());
		lTelephone.setText(op.getTelephone());
		lType.setText(op.getType());
	}
}
