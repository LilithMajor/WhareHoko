import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import database.Database;

/**
 * 
 */

/**
 * @author Asus
 *
 */
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer = new Timer();
		DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dfm.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
		Date a = new Date();
		long temps = 86400000;
		try {
			a = dfm.parse("2017-05-30 05:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  Database db = Database.getDatabase();
				  ArrayList<String> emails = new ArrayList<String>();
				  try {
						emails = db.deleteOldAppart();
					} catch (ClassNotFoundException e) {
						System.out.println("ClassNotFound");
					} catch (SQLException e) {
						System.out.println("SQLException");
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				  for(String email : emails) {
					  sendMail(email);
				  }
			  }
			}, a, temps);
	}
	
	private void sendMail(String email) {
		final String username = "wharehoko0@gmail.com";
		final String password = "wharehokothe";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String msg = "Attention ! Vous avez des appartements en vente depuis plus de 3 mois ! Ils sont donc supprimés.";

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("wharehoko0@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Vos appartements WhareHoko");
			message.setContent(msg, "text/html; charset=utf-8");
			message.saveChanges();

			Transport.send(message);


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
  }

}
