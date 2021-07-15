package vue.operation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.entites.Operateur;
import model.entites.Produit;
import model.entites.ProduitStat;
import myUX.MyButton;
import myUX.MyComboBox;
import myUX.MyLabel;
import myUX.MyPanel;
import myUX.MyTextField;
import vue.component.Form;

@SuppressWarnings("serial")
public class FormOperation extends Form {

	private MyPanel panelCentreCentre;
	private MyPanel panelCentreCentreGauche;
	private MyPanel panelCentreCentreDroite;
	private MyPanel panelCentreSud;
	private JLabel lType;
	private JLabel lQuantite;
	private JLabel lPrixUnitaite;
	private JLabel lProduit;
	private JLabel lOperateur;
	private JComboBox<String> comboType;
	private JTextField txtQuantite;
	private JTextField txtPrixUnitaire;
	private JComboBox<String> comboProduit;
	private JComboBox<String> comboOperateur;
	
	private ArrayList<ProduitStat> produitItems;
	private ArrayList<Operateur> operateurItems;
	private String[] typeItems;

	public FormOperation() {
		super();
		// UI
		produitItems = new ArrayList<ProduitStat>();
		operateurItems = new ArrayList<Operateur>();
		typeItems = new String[] {};

		// CENTER CENTER --> FORM
		lType = new MyLabel("Type");
		lQuantite = new MyLabel("Quantité");
		lPrixUnitaite = new MyLabel("Prix Unitaire");
		lProduit = new MyLabel("Produit");
		lOperateur = new MyLabel("Opérateur");
		comboType = new MyComboBox();
		txtQuantite = new MyTextField(45);
		txtPrixUnitaire = new MyTextField(45);
		comboProduit = new MyComboBox();
		comboOperateur = new MyComboBox();
		panelCentreCentre = new MyPanel();
		panelCentreCentreGauche = new MyPanel();
		panelCentreCentreDroite = new MyPanel();
		panelCentreCentre.setLayout(new GridLayout(1, 2, 30, 0));
		panelCentreCentre.add(panelCentreCentreGauche);
		panelCentreCentre.add(panelCentreCentreDroite);

		// CENTER CENTER GAUCHE
		panelCentreCentreGauche.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentreCentreGauche.add(lType);
		panelCentreCentreGauche.add(comboType);
		panelCentreCentreGauche.add(lPrixUnitaite);
		panelCentreCentreGauche.add(txtPrixUnitaire);
		panelCentreCentreGauche.add(lOperateur);
		panelCentreCentreGauche.add(comboOperateur);
		
		// CENTER CENTER DROITE
		panelCentreCentreDroite.setLayout(new GridLayout(6, 1, 0, 5));
		panelCentreCentreDroite.add(lQuantite);
		panelCentreCentreDroite.add(txtQuantite);
		panelCentreCentreDroite.add(lProduit);
		panelCentreCentreDroite.add(comboProduit);

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
		panelCentre.setBorder(new EmptyBorder(0, 90, 50, 90));
	}


	public void setTypeItems(String[] types) {
		this.typeItems = types;
		comboType.removeAllItems();
		for (String type : this.typeItems) {
			comboType.addItem(type);
		}
	}
	
	public void setProduitItems(ArrayList<ProduitStat> produits) {
		this.produitItems = produits;
		comboProduit.removeAllItems();
		for (Produit produit : this.produitItems) {
			comboProduit.addItem(produit.getNom());
		}
	}

	public void setOperateurItems(ArrayList<Operateur> operateurs) {
		this.operateurItems = operateurs;
		comboOperateur.removeAllItems();
		for (Operateur op : this.operateurItems) {
			comboOperateur.addItem(op.getNom() + " --- " + op.getTelephone());
		}
	}

	
	public String getSelectedType() {
		return (String) comboType.getSelectedItem();
	}
	
	public void setSelectedType(String type) {
		comboType.setSelectedItem(type);
	}
	
	public int getQuantite() {
		return Integer.valueOf(txtQuantite.getText());
	}
	
	public void setQuantite(int quantite) {
		txtQuantite.setText(String.valueOf(quantite));
	}
	
	public int getPrixUnitaire() {
		return Integer.valueOf(txtPrixUnitaire.getText());
	}
	
	public void setPrixUnitaire(int prixUnitaire) {
		txtPrixUnitaire.setText(String.valueOf(prixUnitaire));
	}
	
	public ProduitStat getSelectedProduit() {
		return produitItems.get(comboProduit.getSelectedIndex());
	}
	
	public void setSelectedProduit(Produit p) {
		for (int i = 0; i < produitItems.size(); i++) {
			if (produitItems.get(i).getId() == p.getId()) {
				comboProduit.setSelectedIndex(i);
				break;
			}
		}
	}
	
	public Operateur getSelectedOperateur() {
		return operateurItems.get(comboOperateur.getSelectedIndex());
	}
	
	public void setSelectedOperateur(Operateur op) {
		for (int i = 0; i < operateurItems.size(); i++) {
			if (operateurItems.get(i).getId() == op.getId()) {
				comboOperateur.setSelectedIndex(i);
				break;
			}
		}
	}
	
	public void initJTF() {
		setQuantite(0);
		setPrixUnitaire(0);
	}
}
