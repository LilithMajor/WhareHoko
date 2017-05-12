package com;

public class Proprietaire {
	private String login;
	private String nom;
	private String email;
	private String motdepasse;
	
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
	
	


}
