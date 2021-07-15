package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.dao.implementation.OperateurDaoImp;
import model.dao.interfaces.OperateurDao;
import model.entites.Operateur;
import vue.operateur.VueOperateur;

public class OperateurController implements ActionListener, ListSelectionListener {

	private OperateurDao dao;
	private VueOperateur vue;
	private Long selectedID = 0L;
	private String typeOperateur;

	
	public OperateurController(String typeOperateur) {
		this.typeOperateur = typeOperateur;
		dao = new OperateurDaoImp();
		vue = new VueOperateur();
		vue.setListSelectionListener(this);
		vue.setInfoActionListener(this);
		vue.setFormActionListener(this);
		designVue();
		index();
	}

	private void designVue() {
		if (typeOperateur == Operateur.TYPE_FOURNISSEUR) {
			vue.setEntiteIcon("Fournisseur", "img02-fournisseur.png");
			vue.getInfo().setImageIcon("img02-fournisseur.png");
		}
		if (typeOperateur == Operateur.TYPE_CLIENT) {
			vue.setEntiteIcon("Client", "img03-client.png");
			vue.getInfo().setImageIcon("img03-client.png");
		}
	}
	
	public VueOperateur getVue() {
		return vue;
	}

	
	public void index() {
		try {
			if (typeOperateur == Operateur.TYPE_FOURNISSEUR)
				vue.getTable().setOperateurs(dao.onlyFournisseur());
			if (typeOperateur == Operateur.TYPE_CLIENT)
				vue.getTable().setOperateurs(dao.onlyClient());
			vue.showTable();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void create() {
		vue.showCreate();
	}


	public void store() {
		Operateur op = new Operateur();
		op.setNom(vue.getFormCreation().getNom());
		op.setAdresse(vue.getFormCreation().getAdresse());
		op.setTelephone(vue.getFormCreation().getTelephone());
		op.setType(typeOperateur);
		try {
			dao.store(op);
			vue.showSuccessMessage("Enregisté avec succes");
			vue.getFormCreation().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void info() {
		try {
			vue.getInfo().setOperateurStat(dao.findById(selectedID));
			vue.getInfo().setOperations(dao.getOperations(selectedID));
			vue.showInfo();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void edit() {
		try {
			Operateur op = dao.findById(selectedID);
			vue.getFormEdition().setNom(op.getNom());
			vue.getFormEdition().setAdresse(op.getAdresse());
			vue.getFormEdition().setTelephone(op.getTelephone());
			vue.showEdit();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void update() {
		Operateur op = new Operateur();
		op.setId(selectedID);
		op.setNom(vue.getFormEdition().getNom());
		op.setAdresse(vue.getFormEdition().getAdresse());
		op.setTelephone(vue.getFormEdition().getTelephone());
		try {
			dao.update(op);
			vue.showSuccessMessage("Modifié avec succes");
			vue.getFormEdition().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	private void delete() {
		Operateur op = new Operateur();
		op.setId(selectedID);
		try {
			dao.delete(op);
			vue.showSuccessMessage("Supprimé avec succes");
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
				Operateur op = vue.getTable().getOperateurAt(selectedRow);
				selectedID = op.getId();
				info();
			}
		}
	}
}
