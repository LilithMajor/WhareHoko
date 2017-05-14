import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Appartement;

public class Index extends HttpServlet {

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		RequetesBDD form = new RequetesBDD();
		ArrayList<Appartement> apparts = new ArrayList();
		try {
			apparts = form.getAllAppartements(request);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFound");
		} catch (SQLException e) {
			System.out.println("SQLException");
			System.out.println(e.getMessage());
		}
		request.setAttribute("apparts", apparts);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward( request, response );
	}
	
}
