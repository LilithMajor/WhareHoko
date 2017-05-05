

import java.io.IOException;
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
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    public static final String VUE              = "/WEB-INF/connexion.html";

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	Locale localeFR = new Locale("FR","fr");
        Calendar calendrier = Calendar.getInstance(localeFR );
        DateFormat format = DateFormat.getDateTimeInstance();
        format.setCalendar(calendrier);
        String date = format.format(calendrier.getTime()).toString();
        
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();

        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession(true);

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            session.setAttribute("date", date);
            System.out.println(request);
            System.out.println(response);
        this.getServletContext().getRequestDispatcher("/WEB-INF/affiche.jsp").forward( request, response );
    }
}