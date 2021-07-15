package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlFactory {

	private static String diver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/";
	private static String database = "DB_SAMASTOCK";
	private static String user = "dev";
	private static String password = "Passe123";
	
	
	public Connection openConnection() {
		try {
			Class.forName(diver);
			Connection cnx = DriverManager.getConnection(url + database, user, password);
			return cnx;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("Erreur lors de la connexion à la base donnees");
		}
		return null;
	}
}
