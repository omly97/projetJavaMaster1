package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.dao.implementation.ProduitDaoImp;
import model.dao.interfaces.ProduitDao;
import model.entites.Produit;
import model.entites.ProduitStat;
import vue.produit.VueProduit;

public class ProduitController implements ActionListener, ListSelectionListener {

	private ProduitDao dao;
	private VueProduit vue;
	private Long selectedID = 0L;


	public ProduitController() {
		super();
		dao = new ProduitDaoImp();
		vue = new VueProduit();
		vue.setListSelectionListener(this);
		vue.setInfoActionListener(this);
		vue.setFormActionListener(this);
		index();
	}

	public VueProduit getVue() {
		return vue;
	}
	
	public void index() {
		try {
			vue.getTable().setProduits(dao.all());
			vue.showTable();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void create() {
		vue.showCreate();
	}

	public void store() {
		Produit p = new Produit();
		p.setNom(vue.getFormCreation().getNom());
		p.setCategorie(vue.getFormCreation().getCategorie());
		try {
			dao.store(p);
			vue.showSuccessMessage("Produit enregisté avec succes");
			vue.getFormCreation().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void show() {
		try {
			vue.getInfo().setProduitStat(dao.findById(selectedID));
			vue.getInfo().setOperations(dao.getOperations(selectedID));
			vue.showInfo();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void edit() {
		try {
			ProduitStat p = dao.findById(selectedID);
			vue.getFormEdition().setNom(p.getNom());
			vue.getFormEdition().setCategorie(p.getCategorie());
			vue.showEdit();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void update() {
		Produit p = new Produit();
		p.setId(selectedID);
		p.setNom(vue.getFormEdition().getNom());
		p.setCategorie(vue.getFormEdition().getCategorie());
		try {
			dao.update(p);
			vue.showSuccessMessage("Produit modifié avec succes");
			vue.getFormEdition().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void delete() {
		Produit p = new Produit();
		p.setId(selectedID);
		try {
			dao.delete(p);
			vue.showSuccessMessage("Produit supprimé avec succes");
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vue.getFormCreation().getbSoumettre()) {
			store();
		}
		if (e.getSource() == vue.getInfo().getbEditer()) {
			String strId = e.getActionCommand();
			selectedID = Long.valueOf(strId);
			edit();
		}
		if (e.getSource() == vue.getFormEdition().getbSoumettre()) {
			update();
		}
		if (e.getSource() == vue.getInfo().getbSupprimer()) {
			String strId = e.getActionCommand();
			selectedID = Long.valueOf(strId);
			delete();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (!lsm.isSelectionEmpty()) {
				int selectedRow = lsm.getMinSelectionIndex();
				ProduitStat p = vue.getTable().getProduitAt(selectedRow);
				selectedID = p.getId();
				show();
			}
		}
	}
}
