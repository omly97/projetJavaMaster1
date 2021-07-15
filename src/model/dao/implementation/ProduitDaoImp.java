package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.interfaces.ProduitDao;
import model.entites.Operation;
import model.entites.Produit;
import model.entites.ProduitStat;

public class ProduitDaoImp extends DaoImpl implements ProduitDao {
	
	public ProduitDaoImp() {
		super();
	}

	@Override
	public ArrayList<ProduitStat> all() throws SQLException {
		String query = "SELECT * FROM view_stock_produits";
		ArrayList<ProduitStat> res = new ArrayList<ProduitStat>();
		Connection cnx = this.mysqlFactory.openConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
			res.add(getProduitStock(rs));
		cnx.close();
		return res;
	}

	@Override
	public int store(Produit produit) throws SQLException {
		String query = "INSERT INTO produits(nom, categorie) VALUES(?, ?)";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, produit.getNom());
		pStmt.setString(2, produit.getCategorie());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public ProduitStat findById(Long id) throws SQLException {
		String query = "SELECT * FROM view_produits WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.first();
		ProduitStat produit = getProduitStockFinance(rs);
		cnx.close();
		return produit;
	}
	
	@Override
	public ArrayList<Operation> getOperations(Long id) throws SQLException {
		String query = "SELECT * FROM view_operations WHERE p_id = ?";
		ArrayList<Operation> res = new ArrayList<Operation>();
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, id);
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			Operation op = OperationDaoImp.getOperation(rs);
			op.setOperateur(OperationDaoImp.getOperateur(rs));
			res.add(op);
		}
		cnx.close();
		return res;
	}

	@Override
	public int update(Produit produit) throws SQLException {
		String query = "UPDATE produits SET nom = ?, categorie = ? WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, produit.getNom());
		pStmt.setString(2, produit.getCategorie());
		pStmt.setLong(3, produit.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public int delete(Produit produit) throws SQLException {
		String query = "DELETE FROM produits WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, produit.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}


	public static ProduitStat getProduit(ResultSet rs) throws SQLException {
		ProduitStat p = new ProduitStat();
		p.setId(rs.getLong("id"));
		p.setNom(rs.getString("nom"));
		p.setCategorie(rs.getString("categorie"));
		return p;
	}

	public static  ProduitStat getProduitStock(ResultSet rs) throws SQLException {
		ProduitStat p = getProduit(rs);
		p.setQuantiteApprovision(rs.getInt("qte_approvision"));
		p.setQuantiteVente(rs.getInt("qte_vente"));
		p.setQuantiteStock(rs.getInt("qte_stock"));
		return p;
	}

	public static  ProduitStat getProduitStockFinance(ResultSet rs) throws SQLException {
		ProduitStat p = getProduitStock(rs);
		p.setMontantAchat(rs.getInt("montant_achat"));
		p.setMontantEmprunt(rs.getInt("montant_emprunt"));
		p.setMontantVente(rs.getInt("montant_vente"));
		p.setMontantPret(rs.getInt("montant_pret"));
		return p;
	}

}
