import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.*;
import javax.servlet.http.*;

import com.Utilisateur;


public class TestServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { 
        HttpSession session = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Locale localeFR = new Locale("FR","fr");
        Calendar calendrier = Calendar.getInstance(localeFR );
         
        DateFormat format = DateFormat.getDateTimeInstance();
        format.setCalendar(calendrier);
       // Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
        Utilisateur user = (Utilisateur) session.getAttribute("SessionUtilisateur");
        
        String name = user.getEmail();
        
	    String title = "Votre adresse e-mail : " + name;

        out.println("<html>");
        
        out.println("<body bgcolor=\"white\">");

        out.println("<h1>" + title + "</h1></n><p>" + " On est le : " + format.format(calendrier.getTime()) + "</p>");
        out.println("</body>");
        out.println("</html>");
    }

}
