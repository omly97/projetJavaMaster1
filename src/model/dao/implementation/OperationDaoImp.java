package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.interfaces.OperationDao;
import model.entites.Operateur;
import model.entites.Operation;
import model.entites.Produit;

public class OperationDaoImp extends DaoImpl implements OperationDao {

	public OperationDaoImp() {
		super();
	}

	@Override
	public ArrayList<Operation> all() throws SQLException {
		String query = "SELECT * FROM view_operations";
		ArrayList<Operation> res = new ArrayList<Operation>();
		Connection cnx = this.mysqlFactory.openConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			Operation op = getOperation(rs);
			op.setProduit(getProduit(rs));
			op.setOperateur(getOperateur(rs));
			res.add(op);
		}
		cnx.close();
		return res;
	}
	
	public ArrayList<Operation> selectByNature(String nature) throws SQLException {
		String query = "SELECT * FROM view_operations WHERE nature = ?";
		ArrayList<Operation> res = new ArrayList<Operation>();
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, nature);
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			Operation op = getOperation(rs);
			op.setProduit(getProduit(rs));
			op.setOperateur(getOperateur(rs));
			res.add(op);
		}
		cnx.close();
		return res;
	}
	
	@Override
	public ArrayList<Operation> onlyApprovision() throws SQLException {
		return selectByNature(Operation.NATURE_APPROVISION);
	}
	
	@Override
	public ArrayList<Operation> onlyVente() throws SQLException {
		return selectByNature(Operation.NATURE_VENTE);
	}

	@Override
	public int store(Operation operation) throws SQLException {
		String query = "INSERT INTO operations(nature, type, quantite, prix_unitaire,"
				+ "operateur_id, produit_id) VALUES(?,?,?,?,?,?)";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, operation.getNature());
		pStmt.setString(2, operation.getType());
		pStmt.setInt(3, operation.getQuantite());
		pStmt.setInt(4, operation.getPrixUnitaire());
		//pStmt.setDate(5, operation.getDateOperation());
		pStmt.setLong(5, operation.getOperateur().getId());
		pStmt.setLong(6, operation.getProduit().getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public Operation findById(Long id) throws SQLException {
		String query = "SELECT * FROM view_operations WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.first();
		Operation op = getOperation(rs);
		op.setProduit(getProduit(rs));
		op.setOperateur(getOperateur(rs));
		cnx.close();
		return op;
	}

	@Override
	public int update(Operation operation) throws SQLException {
		String query = "UPDATE operations SET type=?, quantite=?, prix_unitaire=?,"
				+ "operateur_id=?, produit_id=? WHERE id=?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, operation.getType());
		pStmt.setInt(2, operation.getQuantite());
		pStmt.setInt(3, operation.getPrixUnitaire());
		pStmt.setLong(4, operation.getOperateur().getId());
		pStmt.setLong(5, operation.getProduit().getId());
		pStmt.setLong(6, operation.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public int delete(Operation operation) throws SQLException {
		String query = "DELETE FROM operations WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, operation.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}


	public static Operation getOperation(ResultSet rs) throws SQLException {
		Operation op = new Operation();
		op.setId(rs.getLong("id"));
		op.setNature(rs.getString("nature"));
		op.setType(rs.getString("type"));
		op.setQuantite(rs.getInt("quantite"));
		op.setPrixUnitaire(rs.getInt("prix_unitaire"));
		op.setMontant(rs.getInt("montant"));
		op.setPaiement(rs.getBoolean("paiement"));
		op.setDateOperation(rs.getDate("date_operation"));
		return op;
	}
	
	public static Operateur getOperateur(ResultSet rs) throws SQLException {
		Operateur op = new Operateur();
		op.setId(rs.getLong("op_id"));
		op.setNom(rs.getString("op_nom"));
		op.setAdresse(rs.getString("op_adresse"));
		op.setTelephone(rs.getString("op_telephone"));
		op.setType(rs.getString("op_type"));
		return op;
	}

	public static Produit getProduit(ResultSet rs) throws SQLException {
		Produit p = new Produit();
		p.setId(rs.getLong("p_id"));
		p.setNom(rs.getString("p_nom"));
		p.setCategorie(rs.getString("p_categorie"));
		return p;
	}
}
