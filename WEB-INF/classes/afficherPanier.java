import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Appartement;

public class afficherPanier extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	        HttpSession session = request.getSession(true);
	        ArrayList<Appartement> apparts = (ArrayList<Appartement>) request.getAttribute("panier");
	        request.setAttribute("panier", apparts);
		    this.getServletContext().getRequestDispatcher("/WEB-INF/afficherPanier.jsp").forward( request, response );
	    }
}
