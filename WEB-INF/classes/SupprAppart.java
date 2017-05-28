import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DatabaseException;
import database.Database;

public class SupprAppart extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7954141786830634601L;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException{
    	Database db = Database.getDatabase();
    	String id = request.getParameter("id");
    	try {
    		db.supprimerAppartById(request, id);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	response.sendRedirect(request.getContextPath() + "/consultation");
    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    
    }
}
