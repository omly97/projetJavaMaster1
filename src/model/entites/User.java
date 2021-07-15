package model.entites;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

	protected Long id;
	protected String nom;
	protected String role;
	protected String login;
	protected String password;
	
	public static final String[] ROLES = {"ADMIN", "APPROVISION", "VENTE"};

	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String login, String password) {
		this.login = login;
		this.password = sha1(password);
	}

	public User(String nom, String role, String login, String password) {
		this(login, password);
		this.nom = nom;
		this.role = role;
	}

	public User(Long id, String nom, String role, String login, String password) {
		this(nom, role, login, password);
		this.id = id;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = sha1(password);
	}


	public static String sha1(String input) { 
        try { 
            // getInstance() method is called with algorithm SHA-1 
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
  
            // calculate message digest of the input string 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
  
            // return the HashText 
            return hashtext; 
        }   
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
