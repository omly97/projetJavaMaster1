package vue.operateur;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import img.MyIcon;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class VueOperateur extends MyPanel {

	private ListOperateur table;
	private ShowOperateur info;
	private FormOperateur formCreation;
	private FormOperateur formEdition;
	private CardLayout cardLayout;

	private static final String TABLE = "TABLE";
	private static final String INFO = "INFO";
	private static final String CREATE  = "CREATE";
	private static final String EDIT  = "EDIT";
	

	public VueOperateur() {
		super();
		table = new ListOperateur();
		info = new ShowOperateur();
		formCreation = new FormOperateur();
		formEdition = new FormOperateur();

		cardLayout = new CardLayout();
		setLayout(cardLayout);
		add(TABLE, table);
		add(INFO, info);
		add(CREATE, formCreation);
		add(EDIT, formEdition);
	}

	public ListOperateur getTable() {
		return table;
	}
	
	public ShowOperateur getInfo() {
		return info;
	}
	
	public FormOperateur getFormCreation() {
		return formCreation;
	}
	
	public FormOperateur getFormEdition() {
		return formEdition;
	}

	public void showTable() {
		cardLayout.show(this, TABLE);
	}
	
	public void showInfo() {
		cardLayout.show(this, INFO);
	}
	
	public void showCreate() {
		cardLayout.show(this, CREATE);
	}
	
	public void showEdit() {
		cardLayout.show(this, EDIT);
	}
	
	public void setEntiteIcon(String entite, String icon) {
		formCreation.setTitle("Créer " + entite);
		formCreation.setIcon(MyIcon.createImageIcon(200, 200, icon));
		formEdition.setTitle("Éditer " + entite);
		formEdition.setIcon(MyIcon.createImageIcon(200, 200, icon));
	}
	
	public void setFormActionListener(ActionListener a) {
		formCreation.getbSoumettre().addActionListener(a);
		formEdition.getbSoumettre().addActionListener(a);
	}

	public void setInfoActionListener(ActionListener a) {
		info.getbEditer().addActionListener(a);
		info.getbSupprimer().addActionListener(a);
	}
	
	public void setListSelectionListener(ListSelectionListener listener) {
		table.getSelectionModel().addListSelectionListener(listener);
	}

	public void showSuccessMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void showErrorMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Erreur", JOptionPane.WARNING_MESSAGE);
	}
}
