package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.interfaces.UserDao;
import model.entites.User;

public class UserDaoImpl extends DaoImpl implements UserDao {
	
	public UserDaoImpl() {
		super();
	}

	@Override
	public ArrayList<User> all() throws SQLException {
		String query = "SELECT * FROM users";
		ArrayList<User> res = new ArrayList<User>();
		Connection cnx = this.mysqlFactory.openConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
			res.add(getUser(rs));
		cnx.close();
		return res;
	}

	@Override
	public int store(User user) throws SQLException {
		String query = "INSERT INTO users(nom, role, login, password) VALUES(?, ?, ?, ?)";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, user.getNom());
		pStmt.setString(2, user.getRole());
		pStmt.setString(3, user.getLogin());
		pStmt.setString(4, user.getPassword());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public User findById(Long id) throws SQLException {
		String query = "SELECT * FROM users WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.first();
		User user = getUser(rs);
		cnx.close();
		return user;
	}

	@Override
	public int update(User user) throws SQLException {
		String query = "UPDATE users SET nom = ?, role = ?, login = ? WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, user.getNom());
		pStmt.setString(2, user.getRole());
		pStmt.setString(3, user.getLogin());
		pStmt.setLong(4, user.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public int delete(User user) throws SQLException {
		String query = "DELETE FROM users WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, user.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}


	public static User getUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setNom(rs.getString("nom"));
		user.setRole(rs.getString("role"));
		user.setLogin(rs.getString("login"));
		return user;
	}

}
