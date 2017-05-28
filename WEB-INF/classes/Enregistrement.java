

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Appartement;
import com.Proprietaire;

import Exception.DatabaseException;
import database.Database;

public class Enregistrement extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3731449537640734889L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ){
    	try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/enregistrement.jsp").forward( request, response );
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        /* Pr�paration de l'objet formulaire */
    	 Database db = Database.getDatabase();
        HttpSession session = request.getSession(true);
        Boolean erreur = false;
        String parent = "enregistrement";
        Proprietaire prop = new Proprietaire();
        ArrayList<Appartement> apparts = new ArrayList<Appartement>();

        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
		try {
			prop = db.enregistrerUtilisateur( request );
			apparts = db.getAllAppartements(request);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (DatabaseException e) {
			System.out.println("SQLException");
		} catch (NullPointerException e){
			erreur = true;
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/WEB-INF/enregistrement.jsp").forward( request, response );
		}

        /* R�cup�ration de la session depuis la requ�te */
        

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur � la session, sinon suppression du bean de la session.
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