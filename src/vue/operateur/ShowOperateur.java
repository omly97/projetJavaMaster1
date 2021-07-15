package vue.operateur;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.entites.OperateurStat;
import model.entites.Operation;
import myUX.MyPanel;
import vue.component.InfoModel;
import vue.operation.ListOperation;

@SuppressWarnings("serial")
public class ShowOperateur extends JPanel {

	private InfoModel panelNordInfo;
	private CardOperateur cardOperateur;
	private MyPanel panelCentre;
	private StatsOperateur panelCentreStats;
	private ListOperation panelCentreOperations;


	public ShowOperateur() {
		super();
		// UI
		panelNordInfo = new InfoModel();
		panelCentre = new MyPanel();
		panelCentreStats = new StatsOperateur();
		panelCentreOperations = new ListOperation();
		
		// NORTH
		cardOperateur = new CardOperateur(false);
		panelNordInfo.setPanelOnCenter(cardOperateur);
		
		
		// CENTER
		panelCentre = new MyPanel();
		panelCentre.setLayout(new BoxLayout(panelCentre, BoxLayout.Y_AXIS));
		panelCentre.add(panelCentreStats);
		panelCentre.add(panelCentreOperations);
		
		
		// BORDER
		panelCentreStats.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		// THIS PANEL
		setLayout(new BorderLayout());
		add(panelNordInfo, BorderLayout.NORTH);
		add(panelCentre, BorderLayout.CENTER);
	}
	
	public void setOperateurStat(OperateurStat op) {
		cardOperateur.setOperateur(op);		
		panelCentreStats.setOperateurStat(op);
		getbEditer().setActionCommand(String.valueOf(op.getId()));
		getbSupprimer().setActionCommand(String.valueOf(op.getId()));
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
