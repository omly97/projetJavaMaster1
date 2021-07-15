package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.interfaces.OperateurDao;
import model.entites.Operateur;
import model.entites.OperateurStat;
import model.entites.Operation;

public class OperateurDaoImp extends DaoImpl implements OperateurDao {

	public OperateurDaoImp() {
		super();
	}
	
	@Override
	public ArrayList<Operateur> all() throws SQLException {
		String query = "SELECT * FROM operateurs";
		ArrayList<Operateur> res = new ArrayList<Operateur>();
		Connection cnx = this.mysqlFactory.openConnection();
		Statement stmt = cnx.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next())
			res.add(getOperateur(rs));
		cnx.close();
		return res;
	}
	
	private ArrayList<Operateur> selectByType(String type) throws SQLException {
		String query = "SELECT * FROM operateurs WHERE type = ?";
		ArrayList<Operateur> res = new ArrayList<Operateur>();
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, type);
		ResultSet rs = pStmt.executeQuery();
		while (rs.next())
			res.add(getOperateur(rs));
		cnx.close();
		return res;
	}

	@Override
	public ArrayList<Operateur> onlyFournisseur() throws SQLException {
		return selectByType(Operateur.TYPE_FOURNISSEUR);
	}
	
	@Override
	public ArrayList<Operateur> onlyClient() throws SQLException {
		return selectByType(Operateur.TYPE_CLIENT);
	}

	@Override
	public int store(Operateur operateur) throws SQLException {
		String query = "INSERT INTO operateurs(nom, adresse, telephone, type) VALUES (?,?,?,?)";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, operateur.getNom());
		pStmt.setString(2, operateur.getAdresse());
		pStmt.setString(3, operateur.getTelephone());
		pStmt.setString(4, operateur.getType());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public OperateurStat findById(Long id) throws SQLException {
		String query = "SELECT * FROM view_finance_operateurs WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, id);
		ResultSet rs = pStmt.executeQuery();
		rs.first();
		OperateurStat op = getOperateurFinance(rs);
		cnx.close();
		return op;
	}
	
	@Override
	public ArrayList<Operation> getOperations(Long id) throws SQLException {
		String query = "SELECT * FROM view_operations WHERE op_id = ?";
		ArrayList<Operation> res = new ArrayList<Operation>();
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, id);
		ResultSet rs = pStmt.executeQuery();
		while (rs.next()) {
			Operation op = OperationDaoImp.getOperation(rs);
			op.setProduit(OperationDaoImp.getProduit(rs));
			res.add(op);
		}
		cnx.close();
		return res;
	}

	@Override
	public int update(Operateur operateur) throws SQLException {
		String query = "UPDATE operateurs SET nom=?, adresse=?, telephone=? WHERE id=?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setString(1, operateur.getNom());
		pStmt.setString(2, operateur.getAdresse());
		pStmt.setString(3, operateur.getTelephone());
		pStmt.setLong(4, operateur.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}

	@Override
	public int delete(Operateur operateur) throws SQLException {
		String query = "DELETE FROM operateurs WHERE id = ?";
		Connection cnx = this.mysqlFactory.openConnection();
		PreparedStatement pStmt = cnx.prepareStatement(query);
		pStmt.setLong(1, operateur.getId());
		int res = pStmt.executeUpdate();
		cnx.close();
		return res;
	}
	
	public static OperateurStat getOperateur(ResultSet rs) throws SQLException {
		OperateurStat op = new OperateurStat();
		op.setId(rs.getLong("id"));
		op.setNom(rs.getString("nom"));
		op.setAdresse(rs.getString("adresse"));
		op.setTelephone(rs.getString("telephone"));
		op.setType(rs.getString("type"));
		return op;
	}
	
	public static OperateurStat getOperateurFinance(ResultSet rs) throws SQLException {
		OperateurStat op = getOperateur(rs);
		op.setMontantPaye(rs.getInt("montant_paye"));
		op.setMontantDette(rs.getInt("montant_dette"));
		return op;
	}
}
