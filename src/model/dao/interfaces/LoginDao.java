package model.dao.interfaces;

import java.sql.SQLException;

import model.entites.User;

public interface LoginDao {

	boolean login(String login, String password) throws SQLException;
	
	User getAuthUser(String login) throws SQLException;
}
