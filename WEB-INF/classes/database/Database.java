package database;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.Appartement;
import com.Proprietaire;

import Exception.DatabaseException;

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
<<<<<<< HEAD
	
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
=======

	public Proprietaire connecterUtilisateur( HttpServletRequest request ) throws DatabaseException, ClassNotFoundException {
		try {
			return db.connecterUtilisateur(request);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public Proprietaire enregistrerUtilisateur(HttpServletRequest request)throws ClassNotFoundException, DatabaseException {
		try {
			return db.enregistrerUtilisateur(request);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public ArrayList<Appartement> getAllAppartements(HttpServletRequest request)throws ClassNotFoundException, DatabaseException {
		try {
			return db.getAllAppartements(request);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public void addAppart(HttpServletRequest request, Proprietaire attribute) throws ClassNotFoundException, DatabaseException {
		try{
			db.addAppart(request, attribute);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public ArrayList<Appartement> getAppartByProp(HttpServletRequest request, Proprietaire attribute) throws ClassNotFoundException, DatabaseException {
		try {
			return db.getAppartByProp(request, attribute);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public Appartement getAppartById(HttpServletRequest request, String attribute) throws ClassNotFoundException, DatabaseException {
		try {
			return db.getAppartById(request, attribute);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public void setMontantVenteAppart(HttpServletRequest request, String attribute, String prix) throws ClassNotFoundException, DatabaseException {
		try {
			db.setMontantVenteAppart(request, attribute, prix);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}

	public void supprimerAppartById(HttpServletRequest request, String id) throws ClassNotFoundException, DatabaseException {
		try {
			db.supprimerAppart(request, id);
		}
		catch(SQLException sql){
			throw new DatabaseException();
		}
	}
>>>>>>> 8bf530517bdc84ca9549adeb7448f22adefb0c8c
}
