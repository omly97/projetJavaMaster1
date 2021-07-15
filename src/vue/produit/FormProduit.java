package vue.produit;

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
public class FormProduit extends Form {

	private MyPanel panelCentreCentre;
	private MyPanel panelCentreSud;
	private JLabel lNom;
	private JLabel lCategorie;
	private JTextField txtNom;
	private JTextField txtCategorie;

	public FormProduit() {
		super();
		// CENTER CENTER --> FORM
		lNom = new MyLabel("Nom");
		lCategorie = new MyLabel("Categorie");
		txtNom = new MyTextField(45);
		txtCategorie = new MyTextField(45);
		panelCentreCentre = new MyPanel();
		panelCentreCentre.setLayout(new GridLayout(4, 1, 0, 5));
		panelCentreCentre.add(lNom);
		panelCentreCentre.add(txtNom);
		panelCentreCentre.add(lCategorie);
		panelCentreCentre.add(txtCategorie);

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
		panelCentre.setBorder(new EmptyBorder(0, 150, 130, 150));
	}
	
	public String getNom() {
		return txtNom.getText();
	}
	
	public void setNom(String nom) {
		txtNom.setText(nom);
	}
	
	public String getCategorie() {
		return txtCategorie.getText();
	}
	
	public void setCategorie(String categorie) {
		txtCategorie.setText(categorie);
	}
	
	public void initJTF() {
		setNom("");
		setCategorie("");
	}
}
