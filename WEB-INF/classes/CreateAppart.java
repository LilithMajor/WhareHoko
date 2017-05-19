import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Appartement;
import com.Proprietaire;

public class CreateAppart extends HttpServlet{
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ){
    	try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/miseEnVente.jsp").forward( request, response );
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    	System.out.println();
    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        
        /* Préparation de l'objet formulaire */
        RequetesBDD form = new RequetesBDD();
        HttpSession session = request.getSession(true);
        try {
			form.addAppart(request, (Proprietaire) session.getAttribute("sessionUtilisateur"));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /* Traitement de la requête et récupération du bean en résultant */
        response.sendRedirect(request.getContextPath() + "/consultation");
    }
}
