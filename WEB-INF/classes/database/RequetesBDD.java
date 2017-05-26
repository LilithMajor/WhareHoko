package database;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.Appartement;
import com.Proprietaire;


public final class RequetesBDD {
    private static final String CHAMP_LOGIN  = "login";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_NAME   = "nom";
    private static final String CHAMP_EMAIL   = "email";
    private Connection connect;
    
    public RequetesBDD(){
    	try {
			Class.forName("oracle.jdbc.OracleDriver");
			this.connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GRAMMONTG", "GRAMMONTG");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    public Proprietaire connecterUtilisateur( HttpServletRequest request ) throws ClassNotFoundException, SQLException {
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        System.out.println(login);
        System.out.println(motDePasse);
        Proprietaire prop = new Proprietaire();
        Statement statement = connect.createStatement();
    	ResultSet result = statement.executeQuery("SELECT * FROM PROPRIETAIRES WHERE login='"+login+"' AND Mdp='"+motDePasse+"'");
    	if(!result.next()){
    		throw new NullPointerException();
    	}
    	else{
			prop.setNom(result.getString("Nom"));
			prop.setLogin(result.getString("Login"));
			prop.setMotdepasse(result.getString("Mdp"));
			prop.setEmail(result.getString("Email"));
		}       
        return prop;
    }
    
    public Proprietaire enregistrerUtilisateur(HttpServletRequest request)throws ClassNotFoundException, SQLException {
    	Statement statement = connect.createStatement();
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String nom = getValeurChamp(request, CHAMP_NAME);
        String email = getValeurChamp(request, CHAMP_EMAIL);
    	ResultSet result = statement.executeQuery("SELECT login FROM PROPRIETAIRES");
    	while(result.next()){
			if(result.getString(1).equals(login)){
				throw new NullPointerException();
			}
		}
    	Statement statementEnregistrement = connect.createStatement();
    	String sql = "INSERT INTO PROPRIETAIRES VALUES ("+"'"+nom+"',"+"'"+login+"',"+"'"+motDePasse+"',"+"'"+email+"')";
    	statementEnregistrement.executeUpdate(sql);
    	Proprietaire prop = new Proprietaire();
    	prop.setLogin(login);
		prop.setNom(nom);
		prop.setMotdepasse(motDePasse);
		prop.setEmail(email);
		
		return prop;
    }
    
    public ArrayList<Appartement> getAllAppartements(HttpServletRequest request)throws ClassNotFoundException, SQLException{
    	Statement statement = connect.createStatement();
    	ResultSet result = statement.executeQuery("SELECT * FROM APPARTEMENTS");
    	ArrayList<Appartement> apparts = new ArrayList<Appartement>();
    	while(result.next()) {
    		apparts.add(new Appartement(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getDate(5),result.getString(6)));
    	}
    	return apparts;
    }
    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
            return valeur;
    }

	public void addAppart(HttpServletRequest request, Proprietaire attribute) throws SQLException, ClassNotFoundException {
    	Statement statement = connect.createStatement();
        String adresse = getValeurChamp( request, "adresse" );
        String type = getValeurChamp( request, "type" );
        String prix = getValeurChamp(request, "prix");
        String proprio = attribute.getLogin();
        System.out.println(type);
        System.out.println(adresse);
        System.out.println(prix);
        System.out.println(proprio);
    	java.util.Date d = new java.util.Date();
    	DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
    	String df=f.format(d);
    	String sql = "INSERT INTO APPARTEMENTS VALUES (numero_appart.nextval,'"+type+"','"+adresse+"','"+prix+"',DATE '"+df+"','"+proprio+"','0')"; 
    	statement.executeUpdate(sql);
	}

	public ArrayList<Appartement> getAppartByProp(HttpServletRequest request, Proprietaire attribute) throws ClassNotFoundException, SQLException {
    	Statement statement = connect.createStatement();
    	ResultSet result = statement.executeQuery("SELECT * FROM APPARTEMENTS WHERE APPARTEMENTS.LoginProp='"+attribute.getLogin()+"'");
    	ArrayList<Appartement> apparts = new ArrayList();
    	while(result.next()) {
    		apparts.add(new Appartement(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getDate(5),result.getString(6)));
    	}
    	return apparts;
	}
	
	public ArrayList<Appartement> getAppartById(HttpServletRequest request, String attribute) throws ClassNotFoundException, SQLException {
    	Statement statement = connect.createStatement();
    	ResultSet result = statement.executeQuery("SELECT * FROM APPARTEMENTS WHERE APPARTEMENTS.Numero="+attribute);
    	ArrayList<Appartement> apparts = new ArrayList();
    	while(result.next()) {
    		apparts.add(new Appartement(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getDate(5),result.getString(6)));
    	}
    	return apparts;
	}


	public int setMontantVenteAppart(HttpServletRequest request, String attribute, String prix) throws SQLException {
		Statement statement = connect.createStatement();
    	int result = statement.executeUpdate("UPDATE APPARTEMENTS SET montantVente="+prix+" WHERE Numero="+attribute);
    	return result;
	}
}