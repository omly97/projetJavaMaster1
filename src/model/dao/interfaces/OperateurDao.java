package model.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.entites.Operateur;
import model.entites.OperateurStat;
import model.entites.Operation;

public interface OperateurDao {

	ArrayList<Operateur> all() throws SQLException;

	ArrayList<Operateur> onlyFournisseur() throws SQLException;

	ArrayList<Operateur> onlyClient() throws SQLException;
	
	int store(Operateur operateur) throws SQLException;
	
	OperateurStat findById(Long id) throws SQLException;
	
	ArrayList<Operation> getOperations(Long id) throws SQLException;
	
	int update(Operateur operateur) throws SQLException;
	
	int delete(Operateur operateur) throws SQLException;
}
