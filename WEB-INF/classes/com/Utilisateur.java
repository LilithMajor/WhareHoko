package com;

public class Utilisateur {

	private String email;
	private String motdepasse;
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setMotDePasse(String motDePasse) {
		this.motdepasse = motDePasse;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getMotDePasse(){
		return motdepasse;
	}

}
