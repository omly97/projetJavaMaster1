package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.App;
import model.entites.Operateur;
import model.entites.Operation;
import model.entites.User;
import vue.frames.AppFrame;

public class AppController implements ActionListener {
	
	private User user;

	private AppFrame frame;
	
	private ProduitController controlProduit;
	private OperateurController controlFournisseur;
	private OperateurController controlClient;
	private OperationController controlApprovision;
	private OperationController controlVente;
	private UserController controlUser;
	
	private static final String PRODUIT = "PRODUIT";
	private static final String FOURNISSEUR = "FOURNISSEUR";
	private static final String CLIENT = "CLIENT";
	private static final String APPROVISION = "APPROVISION";
	private static final String VENTE = "VENTE";
	private static final String USER = "USER";

	
	public AppController(User user) {
		// INIT THE FRAME AND AUTH USER
		frame = new AppFrame();
		this.user = user;

		// INIT OTHER CONTROLLER
		controlProduit = new ProduitController();
		controlFournisseur = new OperateurController(Operateur.TYPE_FOURNISSEUR);
		controlClient = new OperateurController(Operateur.TYPE_CLIENT);
		controlApprovision = new OperationController(Operation.NATURE_APPROVISION);
		controlVente = new OperationController(Operation.NATURE_VENTE);
		controlUser = new UserController();

		// FRAME COMPONENTS IN FRAME CARD LAYOUT
		frame.getPanelMain().add(PRODUIT, controlProduit.getVue());
		frame.getPanelMain().add(FOURNISSEUR, controlFournisseur.getVue());
		frame.getPanelMain().add(CLIENT, controlClient.getVue());
		frame.getPanelMain().add(APPROVISION, controlApprovision.getVue());
		frame.getPanelMain().add(VENTE, controlVente.getVue());
		frame.getPanelMain().add(USER, controlUser.getVue());

		// LISTENER FOR BUTTONS ON SIDEBAR MENU & TOPBAR
		frame.getSidebar().setActionListener(this);
		frame.getMenubar().setActionListener(this);
		frame.getTopbar().setActionListener(this);
		
		// TOPBAR USERNAME
		frame.getTopbar().setUser(this.user.getNom());
	}

	
	public AppFrame getFrame() {
		return frame;
	}


	private void showCard(String cardStr) {
		frame.getCardLayout().show(frame.getPanelMain(), cardStr);
	}
	
	private void setTopbarTitre(String titre) {
		frame.getTopbar().setTitre(">>> " + titre);
	}


	private void menuActionPerformed(ActionEvent e) {
		// ITEM PRODUIT
		if (e.getSource() == frame.getMenubar().getItemProduit()) {
			controlProduit.create();
			showCard(PRODUIT);
			setTopbarTitre("Produits");
			frame.getSidebar().selectMenu(frame.getSidebar().getbProduit());
		}
		// ITEM FOURNISSEUR
		if (e.getSource() == frame.getMenubar().getItemFournisseur()) {
			controlFournisseur.create();
			showCard(FOURNISSEUR);
			setTopbarTitre("Fournisseurs");
			frame.getSidebar().selectMenu(frame.getSidebar().getbFournisseur());
		}
		// ITEM CLIENT
		if (e.getSource() == frame.getMenubar().getItemClient()) {
			controlClient.create();
			showCard(CLIENT);
			setTopbarTitre("Clients");
			frame.getSidebar().selectMenu(frame.getSidebar().getbClient());
		}
		// ITEM APPROVISION
		if (e.getSource() == frame.getMenubar().getItemApprovision()) {
			controlApprovision.create();
			showCard(APPROVISION);
			setTopbarTitre("Approvisions");
			frame.getSidebar().selectMenu(frame.getSidebar().getbApprovision());
		}
		// ITEM VENTE
		if (e.getSource() == frame.getMenubar().getItemVente()) {
			controlVente.create();
			showCard(VENTE);
			setTopbarTitre("Ventes");
			frame.getSidebar().selectMenu(frame.getSidebar().getbVente());
		}
		// ITEM USER
		if (e.getSource() == frame.getMenubar().getItemUser()) {
			controlUser.create();
			showCard(USER);
			setTopbarTitre("Users");
			frame.getSidebar().selectMenu(frame.getSidebar().getbUser());
		}
	}

	private void sidebarActionPerformed(ActionEvent e) {
		// MENU PRODUIT
		if (e.getSource() == frame.getSidebar().getbProduit()) {
			controlProduit.getVue().showTable();
			showCard(PRODUIT);
			setTopbarTitre("Produits");
		}
		// MENU FOURNISSEUR
		if (e.getSource() == frame.getSidebar().getbFournisseur()) {
			controlFournisseur.getVue().showTable();
			showCard(FOURNISSEUR);
			setTopbarTitre("Fournisseurs");
		}
		// MENU CLIENT
		if (e.getSource() == frame.getSidebar().getbClient()) {
			controlClient.getVue().showTable();
			showCard(CLIENT);
			setTopbarTitre("Clients");
		}
		// MENU APPROVISION
		if (e.getSource() == frame.getSidebar().getbApprovision()) {
			controlApprovision.getVue().showTable();
			showCard(APPROVISION);
			setTopbarTitre("Approvisions");
		}
		// MENU VENTE
		if (e.getSource() == frame.getSidebar().getbVente()) {
			controlVente.getVue().showTable();
			showCard(VENTE);
			setTopbarTitre("Ventes");
		}
		// MENU USER
		if (e.getSource() == frame.getSidebar().getbUser()) {
			controlUser.getVue().showTable();
			showCard(USER);
			setTopbarTitre("Users");
		}
	}
	
	private void topbarActionPerformed(ActionEvent e) {
		if(e.getSource() == frame.getTopbar().getbLogout()) {
			frame.dispose();
			App.main(null);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sidebarActionPerformed(e);
		menuActionPerformed(e);
		topbarActionPerformed(e);
	}
}
