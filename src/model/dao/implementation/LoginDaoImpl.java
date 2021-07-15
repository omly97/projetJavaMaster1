package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.interfaces.LoginDao;
import model.entites.User;

public class LoginDaoImpl extends DaoImpl implements LoginDao {
	
	public LoginDaoImpl() {
		super();
	}

	@Override
	public boolean login(String login, String password) throws SQLException {
		String query = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, login);
		pStmt.setString(2, password);
		ResultSet rs = pStmt.executeQuery();
		rs.first();
		int count = rs.getInt(1);
		cnx.close();
		return count == 1;
	}

	@Override
	public User getAuthUser(String login) throws SQLException {
		String query = "SELECT * FROM users WHERE login = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, login);
		ResultSet rs = pStmt.executeQuery();
		rs.first();
		User user = UserDaoImpl.getUser(rs);
		cnx.close();
		return user;
	}

}
