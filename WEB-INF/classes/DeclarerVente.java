import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.Database;

import com.Appartement;
import com.Proprietaire;

public class DeclarerVente extends HttpServlet{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7954141786830634601L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException{
    	Database db = Database.getDatabase();
    	String id = request.getParameter("id");
    	Appartement appart = new Appartement();
    	try {
			appart = db.getAppartById(request, id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	request.setAttribute("appartaVendre", appart);
    	this.getServletContext().getRequestDispatcher("/WEB-INF/declarerVente.jsp").forward( request, response );
    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	Database db = Database.getDatabase();
    	String id = request.getParameter("id");
    	try {
			db.setMontantVenteAppart(request, id, request.getParameter("prix"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	response.sendRedirect(request.getContextPath() + "/consultation");
    }
}