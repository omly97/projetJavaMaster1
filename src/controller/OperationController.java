package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.dao.implementation.OperateurDaoImp;
import model.dao.implementation.OperationDaoImp;
import model.dao.implementation.ProduitDaoImp;
import model.dao.interfaces.OperationDao;
import model.entites.Operation;
import vue.operation.VueOperation;

public class OperationController implements ActionListener, ListSelectionListener {

	private OperationDao dao;
	private VueOperation vue;
	private Long selectedID = 0L;
	private String natureOperation;
	
	public OperationController(String natureOperation) {
		this.natureOperation = natureOperation;
		dao = new OperationDaoImp();
		vue = new VueOperation();
		vue.setListSelectionListener(this);
		vue.setInfoActionListener(this);
		vue.setFormActionListener(this);
		designVue();
		index();
	}
	
	private void designVue() {
		if (natureOperation == Operation.NATURE_APPROVISION) {
			vue.setEntiteIcon("Approvision", "img04-approvision.png");
			vue.getInfo().setImageIcon("img04-approvision.png");
		}
		if (natureOperation == Operation.NATURE_VENTE) {
			vue.setEntiteIcon("Vente", "img05-vente.png");
			vue.getInfo().setImageIcon("img05-vente.png");
		}
	}
	
	public VueOperation getVue() {
		return vue;
	}

	
	public void index() {
		try {
			if (natureOperation == Operation.NATURE_APPROVISION)
				vue.getTable().setOperations(dao.onlyApprovision());
			if (natureOperation == Operation.NATURE_VENTE)
				vue.getTable().setOperations(dao.onlyVente());
			vue.showTable();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void create() {
		try {
			vue.getFormCreation().setTypeItems(Operation.TYPES);
			vue.getFormCreation().setProduitItems(new ProduitDaoImp().all());
			if (natureOperation == Operation.NATURE_APPROVISION)
				vue.getFormCreation().setOperateurItems(new OperateurDaoImp().onlyFournisseur());
			if (natureOperation == Operation.NATURE_VENTE)
				vue.getFormCreation().setOperateurItems(new OperateurDaoImp().onlyClient());
			vue.showCreate();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void store() {
		Operation op = new Operation();
		op.setNature(natureOperation);
		op.setType(vue.getFormCreation().getSelectedType());
		op.setQuantite(vue.getFormCreation().getQuantite());
		op.setPrixUnitaire(vue.getFormCreation().getPrixUnitaire());
		op.setOperateur(vue.getFormCreation().getSelectedOperateur());
		op.setProduit(vue.getFormCreation().getSelectedProduit());
		try {
			dao.store(op);
			vue.showSuccessMessage("Opération enregistée avec succes");
			vue.getFormCreation().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void show() {
		try {
			vue.getInfo().setOperation(dao.findById(selectedID));
			vue.showInfo();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void edit() {
		try {
			vue.getFormEdition().setTypeItems(Operation.TYPES);
			vue.getFormEdition().setProduitItems(new ProduitDaoImp().all());
			if (natureOperation == Operation.NATURE_APPROVISION)
				vue.getFormEdition().setOperateurItems(new OperateurDaoImp().onlyFournisseur());
			if (natureOperation == Operation.NATURE_VENTE)
				vue.getFormEdition().setOperateurItems(new OperateurDaoImp().onlyClient());

			Operation op = dao.findById(selectedID);
			vue.getFormEdition().setSelectedType(op.getType());
			vue.getFormEdition().setQuantite(op.getQuantite());
			vue.getFormEdition().setPrixUnitaire(op.getPrixUnitaire());
			vue.getFormEdition().setSelectedOperateur(op.getOperateur());
			vue.getFormEdition().setSelectedProduit(op.getProduit());
			vue.showEdit();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void update() {
		Operation op = new Operation();
		op.setId(selectedID);
		op.setType(vue.getFormEdition().getSelectedType());
		op.setQuantite(vue.getFormEdition().getQuantite());
		op.setPrixUnitaire(vue.getFormEdition().getPrixUnitaire());
		op.setOperateur(vue.getFormEdition().getSelectedOperateur());
		op.setProduit(vue.getFormEdition().getSelectedProduit());
		try {
			dao.update(op);
			vue.showSuccessMessage("Opération modifiée avec succes");
			vue.getFormEdition().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void delete() {
		Operation op = new Operation();
		op.setId(selectedID);
		try {
			dao.delete(op);
			vue.showSuccessMessage("Opération supprimée avec succes");
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
				Operation op = vue.getTable().getOperationAt(selectedRow);
				selectedID = op.getId();
				show();
			}
		}
	}
}
