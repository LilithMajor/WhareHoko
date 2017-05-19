import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;
import database.RequetesBDD;
import com.Appartement;
import com.Proprietaire;

public class Consultation extends HttpServlet{
/**
	 * 
	 */
	private static final long serialVersionUID = 4220047334471003445L;
public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	 
        /* Préparation de l'objet formulaire */
    	Database db = Database.getDatabase();
        HttpSession session = request.getSession(true);
        ArrayList<Appartement> apparts = new ArrayList<Appartement>();
        
        /* Traitement de la requête et récupération du bean en résultant */
		try {
			apparts = db.getAppartByProp(request, (Proprietaire) session.getAttribute("sessionUtilisateur"));
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
			System.out.println("SQLException");
		}
		request.setAttribute("appartsAmoi", apparts);
	    this.getServletContext().getRequestDispatcher("/WEB-INF/consulterAppart.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
       
    }
}
