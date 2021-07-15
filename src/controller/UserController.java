package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.dao.implementation.UserDaoImpl;
import model.dao.interfaces.UserDao;
import model.entites.User;
import vue.user.VueUser;

public class UserController implements ActionListener, ListSelectionListener {
	
	private UserDao dao;
	private VueUser vue;
	private Long selectedID = 0L;
	
	
	public UserController() {
		super();
		dao = new UserDaoImpl();
		vue = new VueUser();
		vue.setListSelectionListener(this);
		vue.setInfoActionListener(this);
		vue.setFormActionListener(this);
		index();
	}
	
	public VueUser getVue() {
		return vue;
	}
	
	public void index() {
		try {
			vue.getTable().setUsers(dao.all());
			vue.showTable();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void create() {
		vue.getFormCreation().setRoleItems(User.ROLES);
		vue.showCreate();
	}
	
	public void store() {
		User user = new User();
		user.setNom(vue.getFormCreation().getNom());
		user.setRole(vue.getFormCreation().getSelectedRole());
		user.setLogin(vue.getFormCreation().getLogin());
		user.setPassword("Passe123");
		try {
			dao.store(user);
			vue.showSuccessMessage("User enregisté avec succes");
			vue.getFormCreation().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void show() {
		try {
			vue.getInfo().setUser(dao.findById(selectedID));
			vue.showInfo();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void edit() {
		try {
			vue.getFormEdition().setRoleItems(User.ROLES);

			User user = dao.findById(selectedID);
			vue.getFormEdition().setNom(user.getNom());
			vue.getFormEdition().setSelectedRole(user.getRole());
			vue.getFormEdition().setLogin(user.getLogin());
			vue.showEdit();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}

	public void update() {
		User user = new User();
		user.setId(selectedID);
		user.setNom(vue.getFormEdition().getNom());
		user.setRole(vue.getFormEdition().getSelectedRole());
		user.setLogin(vue.getFormEdition().getLogin());
		try {
			dao.update(user);
			vue.showSuccessMessage("User modifié avec succes");
			vue.getFormEdition().initJTF();
			index();
		} catch (SQLException e) {
			vue.showErrorMessage(e.getMessage());
		}
	}
	
	public void delete() {
		User user = new User();
		user.setId(selectedID);
		try {
			dao.delete(user);
			vue.showSuccessMessage("User supprimé avec succes");
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
				User user = vue.getTable().getUserAt(selectedRow);
				selectedID = user.getId();
				show();
			}
		}
	}
}
