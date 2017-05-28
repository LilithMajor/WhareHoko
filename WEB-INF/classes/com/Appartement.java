package com;

import java.sql.Date;

public class Appartement {
	private int num;
	private String typeAppart;
	private String adresse;
	private int montantVente;
	private Date DatePublication;
	private String loginProp;
	private int vendu;
	
	public Appartement() {
	}
	
	public Appartement(int num, String typeAppart, String adresse, int montantVente, Date DatePublication, String loginProp, int vendu) {
		this.num = num;
		this.typeAppart = typeAppart;
		this.adresse = adresse;
		this.montantVente = montantVente;
		this.DatePublication = DatePublication;
		this.loginProp = loginProp;
		this.vendu = vendu;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTypeAppart() {
		return typeAppart;
	}
	public void setTypeAppart(String typeAppart) {
		this.typeAppart = typeAppart;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getMontantVente() {
		return montantVente;
	}
	public void setMontantVente(int montantVente) {
		this.montantVente = montantVente;
	}
	public Date getDatePublication() {
		return DatePublication;
	}
	public void setDatePublication(Date datePublication) {
		DatePublication = datePublication;
	}
	public String getLoginProp() {
		return loginProp;
	}
	public void setLoginProp(String loginProp) {
		this.loginProp = loginProp;
	}
	public int getVendu() {
		return vendu;
	}
	public void setVendu(int vendu) {
		this.vendu = vendu;
	}

}
