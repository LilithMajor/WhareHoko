package com;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.Appartement;

public class Proprietaire implements HttpSessionBindingListener{
	private String login;
	private String nom;
	private String email;
	private String motdepasse;
	private ArrayList<Appartement> apparts;
	
	public Proprietaire() {
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	@Override
	public String toString() {
		return "Proprietaire [nom=" + nom + ", login=" + login + ", email=" + email + ", motdepasse=" + motdepasse
				+ "]";
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	
	public void setApparts(ArrayList<Appartement> apparts) {
		this.apparts = apparts;
	}
	 @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
		 final String username = "wharehoko0@gmail.com";
			final String password = "wharehokothe";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			
			String msg = "<html>"
					+ "<head>"
					+ "<meta charset='utf-8' />"
					+ "<title>WhareHoko</title>"
					+ "</head>"
					+ "<body><table><tr><th>Numero</th><th>Adresse</th><th>Type</th><th>Montant"
					+ "</th><th>Date de publication</th><th>Proprietaire</th><th>Etat</th><th>Declarer vente</th></tr>";
			
			for(Appartement a : apparts) {
				msg = msg + "<tr><td>"+a.getNum()+"</td><td>"+a.getAdresse()+"</td><td>"+a.getTypeAppart()+"</td><td>"+a.getMontantVente()+
						"</td><td>"+a.getDatePublication()+"</td><td>"+a.getLoginProp()+"</td><td>"+a.getVendu()+"</td></tr>";
			}
			
			msg = msg + "<table></body></html>";

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
					InternetAddress.parse(this.email));
				message.setSubject("Votre selection");
				message.setContent(msg, "text/html; charset=utf-8");
				message.saveChanges();

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
