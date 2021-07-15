package model.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.entites.User;

public interface UserDao {

	ArrayList<User> all() throws SQLException;
	
	int store(User user) throws SQLException;
	
	User findById(Long id) throws SQLException;
	
	int update(User user) throws SQLException;
	
	int delete(User user) throws SQLException;
}
