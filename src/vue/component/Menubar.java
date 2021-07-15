package vue.component;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menubar extends JMenuBar {

	private JMenu menuAction;
	private JMenu menuFenetre;
	private JMenu menuNouveau;
	private JMenuItem itemProduit;
	private JMenuItem itemFournisseur;
	private JMenuItem itemClient;
	private JMenuItem itemApprovision;
	private JMenuItem itemVente;
	private JMenuItem itemUser;
	private JMenuItem itemQuitter;
	private JMenuItem itemScreen;
	
	private final Color BACKGROUND = new Color(33, 150, 243);
	
	public Menubar() {
		super();
		// create UI component
		menuAction = new JMenu("Action");
		menuFenetre = new JMenu("Fenetre");
		menuNouveau = new JMenu("Creer");
		itemProduit = new JMenuItem("Produit");
		itemFournisseur = new JMenuItem("Fournisseur");
		itemClient = new JMenuItem("Client");
		itemApprovision = new JMenuItem("Approvision");
		itemVente = new JMenuItem("Vente");
		itemUser = new JMenuItem("User");
		itemQuitter = new JMenuItem("Quitter");
		itemScreen = new JMenuItem("Toggle Full Screen");
		
		// menu new
		menuNouveau.add(itemProduit);
		menuNouveau.add(itemFournisseur);
		menuNouveau.add(itemClient);
		menuNouveau.add(itemApprovision);
		menuNouveau.add(itemVente);
		menuNouveau.add(itemUser);
		
		// menu action
		menuAction.add(menuNouveau);
		menuAction.add(itemQuitter);
		
		// menu Window
		menuFenetre.add(itemScreen);
		
		// menu bar
		add(menuAction);
		add(menuFenetre);
		
		setBackground(BACKGROUND);
		setBorderPainted(false);
	}

	public JMenuItem getItemProduit() {
		return itemProduit;
	}

	public JMenuItem getItemFournisseur() {
		return itemFournisseur;
	}
	
	public JMenuItem getItemClient() {
		return itemClient;
	}

	public JMenuItem getItemApprovision() {
		return itemApprovision;
	}
	
	public JMenuItem getItemVente() {
		return itemVente;
	}

	public JMenuItem getItemUser() {
		return itemUser;
	}

	public JMenuItem getItemQuitter() {
		return itemQuitter;
	}

	public JMenuItem getItemScreen() {
		return itemScreen;
	}
	
	public void setActionListener(ActionListener a) {
		itemProduit.addActionListener(a);
		itemFournisseur.addActionListener(a);
		itemClient.addActionListener(a);
		itemApprovision.addActionListener(a);
		itemVente.addActionListener(a);
		itemUser.addActionListener(a);
		itemQuitter.addActionListener(a);
		itemScreen.addActionListener(a);
	}
}
