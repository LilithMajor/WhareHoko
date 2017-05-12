

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

import com.Utilisateur;

public class Connexion extends HttpServlet {
    public static final String ATT_SESSION_USER = "sessionUtilisateur";

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        /* Pr�paration de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();
        HttpSession session = request.getSession(true);
        Boolean erreur = false;

        /* Traitement de la requ�te et r�cup�ration du bean en r�sultant */
        String login = null;
		try {
			login = form.connecterUtilisateur( request );
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
			System.out.println("SQLException");
		} catch (NullPointerException e){
			erreur = true;
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward( request, response );
		}

        /* R�cup�ration de la session depuis la requ�te */
        

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur � la session, sinon suppression du bean de la session.
         */
		if(!erreur){
			session.setAttribute( ATT_SESSION_USER, login );
			request.setAttribute("erreur", erreur);
	        this.getServletContext().getRequestDispatcher("/WEB-INF/affiche.jsp").forward( request, response );
		}
    }
}