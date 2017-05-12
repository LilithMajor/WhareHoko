package com;

import java.sql.Date;

public class Appartement {
	private int num;
	private String typeAppart;
	private String adresse;
	private int montantVente;
	private Date DatePublication;
	
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
	private String loginProp;

}
