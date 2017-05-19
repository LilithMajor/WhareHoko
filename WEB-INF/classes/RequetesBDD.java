

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.management.BadAttributeValueExpException;
import javax.servlet.http.HttpServletRequest;

import com.Appartement;
import com.Proprietaire;


public final class RequetesBDD {
    private static final String CHAMP_LOGIN  = "login";
    private static final String CHAMP_PASS   = "motdepasse";
    private static final String CHAMP_NAME   = "nom";
    private static final String CHAMP_EMAIL   = "email";


    public Proprietaire connecterUtilisateur( HttpServletRequest request ) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GRAMMONTG", "GRAMMONTG");
    	Statement statement = connect.createStatement();
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        System.out.println(login);
        System.out.println(motDePasse);
        Proprietaire prop = new Proprietaire();
    	ResultSet result = statement.executeQuery("SELECT * FROM PROPRIETAIRES WHERE login='"+login+"' AND Mdp='"+motDePasse+"'");
    	if(!result.next()){
    		throw new NullPointerException();
    	}
    	else{
			System.out.println("test");
			prop.setNom(result.getString("Nom"));
			prop.setLogin(result.getString("Login"));
			prop.setMotdepasse(result.getString("Mdp"));
			prop.setEmail(result.getString("Email"));
		}       
		System.out.println(prop);
        return prop;
    }
    
    public Proprietaire enregistrerUtilisateur(HttpServletRequest request)throws ClassNotFoundException, SQLException {
    	Class.forName("oracle.jdbc.OracleDriver");
		Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GRAMMONTG", "GRAMMONTG");
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
    	Class.forName("oracle.jdbc.OracleDriver");
		Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GRAMMONTG", "GRAMMONTG");
    	Statement statement = connect.createStatement();
    	ResultSet result = statement.executeQuery("SELECT * FROM APPARTEMENTS");
    	ArrayList<Appartement> apparts = new ArrayList();
    	while(result.next()) {
    		apparts.add(new Appartement(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getDate(5),result.getString(6)));
    	}
    	return apparts;
    }
    /*
     * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
            return valeur;
    }

	public void addAppart(HttpServletRequest request, Proprietaire attribute) throws SQLException, ClassNotFoundException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GRAMMONTG", "GRAMMONTG");
    	Statement statement = connect.createStatement();
        String adresse = getValeurChamp( request, "adresse" );
        String type = getValeurChamp( request, "type" );
        String prix = getValeurChamp(request, "prix");
        String proprio = attribute.getLogin();
        java.util.Date date = new java.util.Date();
    	String sql = "INSERT INTO APPARTEMENTS VALUES ("+"'"+adresse+"',"+"'"+type+"',"+"'"+prix+"','"+proprio+"',"+new java.sql.Date(date.getTime())+")";
    	statement.executeUpdate(sql);
	}

	public ArrayList<Appartement> getAppartByProp(HttpServletRequest request, Proprietaire attribute) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@vs-oracle2:1521:ORCL", "GRAMMONTG", "GRAMMONTG");
    	Statement statement = connect.createStatement();
    	ResultSet result = statement.executeQuery("SELECT * FROM APPARTEMENTS WHERE APPARTEMENTS.LoginProp="+attribute.getLogin());
    	ArrayList<Appartement> apparts = new ArrayList();
    	while(result.next()) {
    		apparts.add(new Appartement(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getDate(5),result.getString(6)));
    	}
    	return apparts;
	}
}