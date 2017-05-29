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
	
	 public ArrayList<String> deleteOldAppart() throws ClassNotFoundException, SQLException{
		return db.deleteOldAppart();
	 }

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
}
