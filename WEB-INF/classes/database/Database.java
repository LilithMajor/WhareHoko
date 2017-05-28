package database;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.Appartement;
import com.Proprietaire;

public class Database {
	private static Database INSTANCE = null;
	private RequetesBDD db;
	
	public Database(){
		this.db = new RequetesBDD();
	}
	
	public static synchronized Database getDatabase(){
		if(INSTANCE == null)
			INSTANCE = new Database();
		return INSTANCE;
	}
	
	 public Proprietaire connecterUtilisateur( HttpServletRequest request ) throws ClassNotFoundException, SQLException {
		 return db.connecterUtilisateur(request);
	 }
	 
	 public Proprietaire enregistrerUtilisateur(HttpServletRequest request)throws ClassNotFoundException, SQLException {
		 return db.enregistrerUtilisateur(request);
	 }
	 
	 public ArrayList<Appartement> getAllAppartements(HttpServletRequest request)throws ClassNotFoundException, SQLException{
		 return db.getAllAppartements(request);
	 }
	 
	 public void addAppart(HttpServletRequest request, Proprietaire attribute) throws SQLException, ClassNotFoundException {
		 db.addAppart(request, attribute);
	 }
	 
	 public ArrayList<Appartement> getAppartByProp(HttpServletRequest request, Proprietaire attribute) throws ClassNotFoundException, SQLException {
		 return db.getAppartByProp(request, attribute);
	 }
	 
	 public Appartement getAppartById(HttpServletRequest request, String attribute) throws ClassNotFoundException, SQLException{
		 return db.getAppartById(request, attribute);
	 }
	 
	 public void setMontantVenteAppart(HttpServletRequest request, String attribute, String prix) throws ClassNotFoundException, SQLException{
		 db.setMontantVenteAppart(request, attribute, prix);
	 }
	 
	 public ArrayList<String> deleteOldAppart() throws ClassNotFoundException, SQLException{
		return db.deleteOldAppart();
	 }
}
