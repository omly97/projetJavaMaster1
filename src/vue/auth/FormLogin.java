package vue.auth;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import myUX.MyAlert;
import myUX.MyButton;
import myUX.MyLabel;
import myUX.MyPanel;
import myUX.MyPasswordField;
import myUX.MyTextField;
import vue.component.Form;

@SuppressWarnings("serial")
public class FormLogin extends Form {
	
	private JPanel panelCentreNord;
	private JPanel panelCentreCentre;
	private JPanel panelCentreSud;
	private MyAlert alert;
	private JLabel lLogin;
	private JLabel lPassword;
	private JTextField txtLogin;
	private JPasswordField txtPassword;


	public FormLogin() {
		super();
		// CENTER NORTH
		alert = new MyAlert("Donner votre login et mot de passe");
		alert.setType(MyAlert.PRIMARY);
		panelCentreNord = new MyPanel();
		panelCentreNord.add(alert);
		
		
		// CENTER CENTER
		lLogin = new MyLabel("Login");
		lPassword = new MyLabel("Mot de passe");
		txtLogin = new MyTextField(45);
		txtPassword = new MyPasswordField(45);
		panelCentreCentre = new MyPanel();
		panelCentreCentre.setLayout(new GridLayout(4, 1, 0, 5));
		panelCentreCentre.add(lLogin);
		panelCentreCentre.add(txtLogin);
		panelCentreCentre.add(lPassword);
		panelCentreCentre.add(txtPassword);
		
		// CENTER SUD
		bSoumettre = new MyButton("Connexion", MyButton.SUCCESS);
		bSoumettre.setPreferredSize(new Dimension(100, 50));
		panelCentreSud = new MyPanel();
		panelCentreSud.setLayout(new GridLayout(1, 1));
		panelCentreSud.add(bSoumettre);
		panelCentreSud.setBorder(new EmptyBorder(15, 0, 0, 0));
		
		// CENTRE
		panelCentre.setLayout(new BorderLayout());
		panelCentre.add(panelCentreNord, BorderLayout.NORTH);
		panelCentre.add(panelCentreCentre, BorderLayout.CENTER);
		panelCentre.add(panelCentreSud, BorderLayout.SOUTH);
		
	}

	public String getLogin() {
		return txtLogin.getText();
	}

	public String getPassword() {
		return String.valueOf(txtPassword.getPassword());
	}
	
	public MyAlert getAlert() {
		return alert;
	}
}
