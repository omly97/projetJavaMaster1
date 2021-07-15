package vue.operation;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.entites.Operation;
import myUX.MyPanel;
import vue.component.InfoModel;
import vue.operateur.CardOperateur;
import vue.produit.CardProduit;

@SuppressWarnings("serial")
public class ShowOperation extends JPanel {

	private InfoModel panelNordInfo;
	private MyPanel panelCentre;
	private JPanel panelCentreGauche;
	private JPanel panelCentreDroite;
	private CardOperation cardOperation;
	private CardOperateur cardOperateur;
	private CardProduit cardProduit;


	public ShowOperation() {
		super();
		// UI
		panelNordInfo = new InfoModel();
		panelCentre = new MyPanel();
		panelCentreGauche = new MyPanel(true);
		panelCentreDroite = new MyPanel(true);

		// NORTH
		cardOperation = new CardOperation();
		panelNordInfo.setPanelOnCenter(cardOperation);
		
		// CENTER
		panelCentre.setLayout(new GridLayout(1, 2, 20, 5));
		panelCentre.add(panelCentreGauche);
		panelCentre.add(panelCentreDroite);
		panelCentre.setBorder(new EmptyBorder(20, 0, 0, 0));
		
		// CENTER GAUCHE
		cardProduit = new CardProduit(true);
		panelCentreGauche.add(cardProduit);

		// CENTER RIGHT UI
		cardOperateur = new CardOperateur(true);
		panelCentreDroite.add(cardOperateur);
		
		// THIS PANEL
		setLayout(new BorderLayout());
		add(panelNordInfo, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
	}
	
	public void setOperation(Operation op) {
		cardOperation.setOperation(op);
		cardOperateur.setOperateur(op.getOperateur());
		cardProduit.setProduit(op.getProduit());
		getbEditer().setActionCommand(String.valueOf(op.getId()));
		getbSupprimer().setActionCommand(String.valueOf(op.getId()));

	}
	
	public void setImageIcon(String icon) {
		panelNordInfo.setIcon(icon);
	}
	
	public JButton getbEditer() {
		return panelNordInfo.getbEditer();
	}
	
	public JButton getbSupprimer() {
		return panelNordInfo.getbSupprimer();
	}
}
