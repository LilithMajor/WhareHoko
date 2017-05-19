import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Appartement;

import database.Database;
import database.RequetesBDD;

public class Index extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1884058970705099571L;

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		Database db = Database.getDatabase();
		ArrayList<Appartement> apparts = new ArrayList<Appartement>();
		try {
			apparts = db.getAllAppartements(request);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
			System.out.println("SQLException");
			System.out.println(e.getMessage());
		}
		request.setAttribute("apparts", apparts);
		request.setAttribute("parent", "accueil");
		this.getServletContext().getRequestDispatcher("/index.jsp").forward( request, response );
	}
	
}
