package vue.operateur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import myUX.MyButton;
import myUX.MyLabel;
import myUX.MyPanel;
import myUX.MyTextField;
import vue.component.Form;

@SuppressWarnings("serial")
public class FormOperateur extends Form {

	private MyPanel panelCentreCentre;
	private MyPanel panelCentreSud;
	private JLabel lNom;
	private JLabel lAdresse;
	private JLabel lTelephone;
	private JTextField txtNom;
	private JTextField txtAdresse;
	private JTextField txtTelephone;

	
	public FormOperateur() {
		super();
		// CENTER CENTER --> FORM
		lNom = new MyLabel("Nom");
		lAdresse = new MyLabel("Adresse");
		lTelephone = new MyLabel("Telephone");
		txtNom = new MyTextField(45);
		txtAdresse = new MyTextField(45);
		txtTelephone = new MyTextField(45);
		panelCentreCentre = new MyPanel();
		panelCentreCentre.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentreCentre.add(lNom);
		panelCentreCentre.add(txtNom);
		panelCentreCentre.add(lAdresse);
		panelCentreCentre.add(txtAdresse);
		panelCentreCentre.add(lTelephone);
		panelCentreCentre.add(txtTelephone);

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

	
	public String getNom() {
		return txtNom.getText();
	}
	
	public void setNom(String nom) {
		txtNom.setText(nom);
	}
	
	public String getAdresse() {
		return txtAdresse.getText();
	}
	
	public void setAdresse(String adresse) {
		txtAdresse.setText(adresse);
	}
	
	public String getTelephone() {
		return txtTelephone.getText();
	}
	
	public void setTelephone(String telephone) {
		txtTelephone.setText(telephone);
	}
	
	public void initJTF() {
		setNom("");
		setAdresse("");
		setTelephone("");
	}
}
