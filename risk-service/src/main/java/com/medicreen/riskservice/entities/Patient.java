package com.medicreen.riskservice.entities;

public class Patient {
	
	private Integer id;
	private String prenom;
	private String nom;
	private String dateDeNaissance;
	private String genre;
	private String adressePostale;
	private String telephone;
	
	
	public Patient() {
		super();
	}


	public Patient(Integer id, String prenom, String nom, String dateDeNaissance, String genre, String adressePostale,
			String telephone) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		this.genre = genre;
		this.adressePostale = adressePostale;
		this.telephone = telephone;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDateDeNaissance() {
		return dateDeNaissance;
	}


	public void setDateDeNaissance(String dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getAdressePostale() {
		return adressePostale;
	}


	public void setAdressePostale(String adressePostale) {
		this.adressePostale = adressePostale;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
