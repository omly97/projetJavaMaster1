package vue.produit;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import img.MyIcon;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class VueProduit extends MyPanel {

	private ListProduit table;
	private ShowProduit info;
	private FormProduit formCreation;
	private FormProduit formEdition;
	private CardLayout cardLayout;
	
	private static final String TABLE = "TABLE";
	private static final String INFO = "INFO";
	private static final String CREATE = "CREATE";
	private static final String EDIT = "EDIT";


	public VueProduit() {
		super();
		table = new ListProduit();
		info = new ShowProduit();
		formCreation = new FormProduit();
		formEdition = new FormProduit();
		
		formCreation.setTitle("Nouveau produit");
		formCreation.setIcon(MyIcon.createImageIcon(200, 200, "img01-produit.png"));

		formEdition.setTitle("Éditer produit");
		formEdition.setIcon(MyIcon.createImageIcon(200, 200, "img01-produit.png"));
		
		info.setImageIcon("img01-produit.png");
	
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		add(TABLE, table);
		add(INFO, info);
		add(CREATE, formCreation);
		add(EDIT, formEdition);
	}

	public ListProduit getTable() {
		return table;
	}
	
	public ShowProduit getInfo() {
		return info;
	}

	public FormProduit getFormCreation() {
		return formCreation;
	}

	public FormProduit getFormEdition() {
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
