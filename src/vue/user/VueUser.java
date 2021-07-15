package vue.user;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import img.MyIcon;
import myUX.MyPanel;

@SuppressWarnings("serial")
public class VueUser extends MyPanel {

	private ListUser table;
	private ShowUser info;
	private FormUser formCreation;
	private FormUser formEdition;
	private CardLayout cardLayout;
	
	private static final String TABLE = "TABLE";
	private static final String INFO = "INFO";
	private static final String CREATE = "CREATE";
	private static final String EDIT = "EDIT";


	public VueUser() {
		super();
		table = new ListUser();
		info = new ShowUser();
		formCreation = new FormUser();
		formEdition = new FormUser();
		
		formCreation.setTitle("Nouveau User");
		formCreation.setIcon(MyIcon.createImageIcon(200, 200, "img06-user.png"));

		formEdition.setTitle("Éditer User");
		formEdition.setIcon(MyIcon.createImageIcon(200, 200, "img06-user.png"));
		
		info.setImageIcon("img06-user.png");
		
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		add(TABLE, table);
		add(INFO, info);
		add(CREATE, formCreation);
		add(EDIT, formEdition);
	}

	public ListUser getTable() {
		return table;
	}

	public ShowUser getInfo() {
		return info;
	}

	public FormUser getFormCreation() {
		return formCreation;
	}

	public FormUser getFormEdition() {
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
