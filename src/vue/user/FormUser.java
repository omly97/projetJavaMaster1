package vue.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import myUX.MyButton;
import myUX.MyComboBox;
import myUX.MyLabel;
import myUX.MyPanel;
import myUX.MyTextField;
import vue.component.Form;

@SuppressWarnings("serial")
public class FormUser extends Form {

	private MyPanel panelCentreCentre;
	private MyPanel panelCentreSud;
	private JLabel lNom;
	private JLabel lRole;
	private JLabel lLogin;
	private JTextField txtNom;
	private JComboBox<String> comboRole;
	private JTextField txtLogin;
	
	private String[] roleItems;
	
	public FormUser() {
		super();
		// UI
		roleItems = new String[] {};

		// CENTER CENTER --> FORM
		lNom = new MyLabel("Nom");
		lRole = new MyLabel("Role");
		lLogin = new MyLabel("Login");
		txtNom = new MyTextField(45);
		comboRole = new MyComboBox();
		txtLogin = new MyTextField(45);
		panelCentreCentre = new MyPanel();
		panelCentreCentre.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentreCentre.add(lNom);
		panelCentreCentre.add(txtNom);
		panelCentreCentre.add(lRole);
		panelCentreCentre.add(comboRole);
		panelCentreCentre.add(lLogin);
		panelCentreCentre.add(txtLogin);

		// CENTER SOUTH --> SUBMIT BUTTON
		bSoumettre = new MyButton("Soumettre", MyButton.SUCCESS);
		bSoumettre.setPreferredSize(new Dimension(100, 50));
		panelCentreSud = new MyPanel();
		panelCentreSud.setLayout(new GridLayout(1, 1));
		panelCentreSud.add(bSoumettre);
		panelCentreSud.setBorder(new EmptyBorder(30, 0, 0, 0));

		// CENTRE
		panelCentre.setLayout(new BorderLayout());
		panelCentre.add(panelCentreCentre, BorderLayout.CENTER);
		panelCentre.add(panelCentreSud, BorderLayout.SOUTH);
		panelCentre.setBorder(new EmptyBorder(0, 150, 50, 150));
	}
	
	
	public void setRoleItems(String[] roles) {
		this.roleItems = roles;
		comboRole.removeAllItems();
		for (String role : this.roleItems) {
			comboRole.addItem(role);
		}
	}
	
	public String getNom() {
		return txtNom.getText();
	}
	
	public void setNom(String nom) {
		txtNom.setText(nom);
	}
	
	public String getSelectedRole() {
		return (String) comboRole.getSelectedItem();
	}
	
	public void setSelectedRole(String type) {
		comboRole.setSelectedItem(type);
	}
	
	public String getLogin() {
		return txtLogin.getText();
	}
	
	public void setLogin(String login) {
		txtLogin.setText(login);
	}
	
	public void initJTF() {
		setNom("");
		setLogin("");
	}
}
