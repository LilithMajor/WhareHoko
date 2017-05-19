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
        ArrayList<Appartement> apparts = new ArrayList();
        
        /* Traitement de la requête et récupération du bean en résultant */
		try {
			apparts = db.getAppartByProp(request, (Proprietaire) session.getAttribute("sessionUtilisateur"));
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
			System.out.println("SQLException");
		}
		request.setAttribute("apparts", apparts);
	    this.getServletContext().getRequestDispatcher("/consulterAppart.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        /* Préparation de l'objet formulaire */
        RequetesBDD form = new RequetesBDD();
        HttpSession session = request.getSession(true);
        Boolean erreur = false;
        ArrayList<Appartement> apparts = new ArrayList();
        String parent = "connexion";
        
        /* Traitement de la requête et récupération du bean en résultant */
        Proprietaire prop = new Proprietaire();
		try {
			prop = form.connecterUtilisateur( request );
			apparts = form.getAllAppartements(request);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
			System.out.println("SQLException");
		} catch (NullPointerException e){
			erreur = true;
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward( request, response );
		}

        /* Récupération de la session depuis la requête */
        

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
		if(!erreur){
			session.setAttribute( ATT_SESSION_USER, prop );
			request.setAttribute("apparts", apparts);
			request.setAttribute("erreur", erreur);
			request.setAttribute("parent", parent);
	        this.getServletContext().getRequestDispatcher("/index.jsp").forward( request, response );
		}
    }
}
