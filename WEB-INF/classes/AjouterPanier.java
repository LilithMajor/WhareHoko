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

import database.Database;

public class AjouterPanier extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		Database db = Database.getDatabase();
		 HttpSession session = request.getSession(true);
		 ArrayList<Appartement> panier = (ArrayList<Appartement>) session.getAttribute("panier");
		 Proprietaire prop = (Proprietaire) session.getAttribute("sessionUtilisateur");
		 Appartement appart = new Appartement();
		 String id = request.getParameter("id");
	    	try {
				appart = db.getAppartById(request, id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    panier.add(appart);
	    prop.setApparts(panier);
	    session.setAttribute("panier", panier);
	    response.sendRedirect(request.getContextPath() + "/index");
	}
}
