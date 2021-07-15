package vue.produit;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import model.entites.Operation;
import model.entites.ProduitStat;
import myUX.MyPanel;
import vue.component.InfoModel;
import vue.operation.ListOperation;

@SuppressWarnings("serial")
public class ShowProduit extends MyPanel {

	private InfoModel panelNordInfo;
	private CardProduit cardProduit;
	private MyPanel panelCentre;
	private StatsProduit panelCentreStats;
	private ListOperation panelCentreOperations;
	

	public ShowProduit() {
		super();
		// UI
		panelNordInfo = new InfoModel();
		panelCentre = new MyPanel();
		panelCentreStats = new StatsProduit();
		panelCentreOperations = new ListOperation();
		
		// NORD
		cardProduit = new CardProduit(false);
		panelNordInfo.setPanelOnCenter(cardProduit);
		
		// CENTER
		panelCentre = new MyPanel();
		panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
		panelCentre.add(panelCentreStats);
		panelCentre.add(panelCentreOperations);

		// BORDER
		panelCentreStats.setBorder(new EmptyBorder(10, 0, 10, 0));

		// THIS PANEL
		setLayout(new BorderLayout());
		add(panelNordInfo, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
	}


	public void setProduitStat(ProduitStat p) {
		cardProduit.setProduit(p);
		panelCentreStats.setProduitStat(p);
		getbEditer().setActionCommand(String.valueOf(p.getId()));
		getbSupprimer().setActionCommand(String.valueOf(p.getId()));
	}
	
	public void setOperations(ArrayList<Operation> operations) {
		panelCentreOperations.setOperations(operations);
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
