

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.management.BadAttributeValueExpException;
import javax.servlet.http.HttpServletRequest;

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
        Proprietaire prop = new Proprietaire();
    	ResultSet result = statement.executeQuery("SELECT * FROM PROPRIETAIRES WHERE login='"+login+"' AND Mdp='"+motDePasse+"'");
    	if(!result.next()){
    		throw new NullPointerException();
    	}
		while(result.next()){
			prop.setLogin(result.getString(1));
			prop.setNom(result.getString(2));
			prop.setMotdepasse(result.getString(3));
			prop.setEmail(result.getString(4));
		}       
		System.out.println(prop);
        return prop;
    }
    
    public void enregistrerUtilisateur(HttpServletRequest request)throws ClassNotFoundException, SQLException {
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
    }
    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
            return valeur;
    }
}