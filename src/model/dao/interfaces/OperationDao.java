package model.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.entites.Operation;

public interface OperationDao {

	ArrayList<Operation> all() throws SQLException;

	ArrayList<Operation> onlyApprovision() throws SQLException;

	ArrayList<Operation> onlyVente() throws SQLException;
	
	int store(Operation operation) throws SQLException;
	
	Operation findById(Long id) throws SQLException;
	
	int update(Operation operation) throws SQLException;
	
	int delete(Operation operation) throws SQLException;
}
