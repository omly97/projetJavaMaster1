package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import model.dao.implementation.LoginDaoImpl;
import model.dao.interfaces.LoginDao;
import model.entites.User;
import myUX.MyAlert;
import vue.frames.LoginFrame;

public class LoginController implements ActionListener {

	private LoginDao dao;
	private LoginFrame frame;
	
	public LoginController() {
		super();
		dao = new LoginDaoImpl();
		frame = new LoginFrame();
		frame.getForm().getbSoumettre().addActionListener(this);
	}

	public void showFrame() {
		frame.setVisible(true);
	}

	public void login() {
		String login = frame.getForm().getLogin();
		String password = frame.getForm().getPassword();
		try {
			if (dao.login(login, User.sha1(password))) {
				frame.dispose();
				User user = dao.getAuthUser(login);
				AppController rootController = new AppController(user);
				rootController.getFrame().setVisible(true);
			}
			else {
				frame.getForm().getAlert().setMessage("Login ou Mot de passe incorrect.");
				frame.getForm().getAlert().setType(MyAlert.DANGER);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frame.getForm().getbSoumettre()) {
			login();
		}
	}
}
