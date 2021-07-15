package model.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import model.entites.Operation;
import model.entites.Produit;
import model.entites.ProduitStat;

public interface ProduitDao {
	
	ArrayList<ProduitStat> all() throws SQLException;

	int store(Produit produit) throws SQLException;
	
	ProduitStat findById(Long id) throws SQLException;
	
	ArrayList<Operation> getOperations(Long id) throws SQLException;
	
	int update(Produit produit) throws SQLException;
	
	int delete(Produit produit) throws SQLException;
}
