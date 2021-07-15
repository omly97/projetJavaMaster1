package vue.operation;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.entites.Operation;
import myUX.MyLabel;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class CardOperation extends MyPanel {

	private JPanel panelGauche;
	private JPanel panelDroite;
	private JLabel lTitreType;
	private JLabel lTitreEtat;
	private JLabel lTitreQuantite;
	private JLabel lTitrePrixUnitaire;
	private JLabel lTitreMontant;
	private JLabel lTitreDateOperation;
	private JLabel lType;
	private JLabel lEtat;
	private JLabel lQuantite;
	private JLabel lPrixUnitaire;
	private JLabel lMontant;
	private JLabel lDateOperation;

	public CardOperation() {
		super(true);
		lTitreType = new MyLabel("Type:", MyLabel.H6);
		lTitreEtat = new MyLabel("Nature:", MyLabel.H6);
		lTitreQuantite = new MyLabel("Quantité:", MyLabel.H6);
		lTitrePrixUnitaire = new MyLabel("Prix unitaire:", MyLabel.H6);
		lTitreMontant =new MyLabel("Montant:", MyLabel.H6);
		lTitreDateOperation = new MyLabel("Date de l'operation:", MyLabel.H6);
		lType = new MyLabel(MyLabel.H5);
		lEtat = new MyLabel(MyLabel.H5);
		lQuantite = new MyLabel(MyLabel.H5);
		lPrixUnitaire = new MyLabel(MyLabel.H5);
		lMontant = new MyLabel(MyLabel.H5);
		lDateOperation = new MyLabel(MyLabel.H5);
		
		// PANEL GAUCHE
		panelGauche = new MyPanel();
		panelGauche.setLayout(new GridLayout(3, 2));
		panelGauche.add(lTitreType);
		panelGauche.add(lType);
		panelGauche.add(lTitreQuantite);
		panelGauche.add(lQuantite);
		panelGauche.add(lTitreMontant);
		panelGauche.add(lMontant);
		
		// PANEL DROITE
		panelDroite = new MyPanel();
		panelDroite.setLayout(new GridLayout(3, 2));
		panelDroite.add(lTitreEtat);
		panelDroite.add(lEtat);
		panelDroite.add(lTitrePrixUnitaire);
		panelDroite.add(lPrixUnitaire);
		panelDroite.add(lTitreDateOperation);
		panelDroite.add(lDateOperation);
		
		// THIS PANEL
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(panelGauche);
		add(panelDroite);
	}
	
	public void setOperation(Operation o) {
		lType.setText(o.getType());
		lEtat.setText(o.getEtat());
		lQuantite.setText(String.valueOf(o.getQuantite()));
		lPrixUnitaire.setText(String.valueOf(o.getPrixUnitaire()) + " F");
		lMontant.setText(String.valueOf(o.getMontant()) + " F");
		lDateOperation.setText(String.valueOf(o.getDateOperation()));
	}
}
