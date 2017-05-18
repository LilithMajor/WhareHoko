

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Proprietaire;

public class Enregistrement extends HttpServlet {
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
        RequetesBDD form = new RequetesBDD();
        HttpSession session = request.getSession(true);
        Boolean erreur = false;
        String parent = "enregistrement";
        Proprietaire prop = new Proprietaire();

        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
		try {
			prop = form.enregistrerUtilisateur( request );
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
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
			request.setAttribute("erreur", erreur);
			request.setAttribute("parent", parent);
	        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward( request, response );
		}
    }
}