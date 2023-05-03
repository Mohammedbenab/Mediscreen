package com.mediscreen.patientservice.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PatientDto {
	
	private String prenom;
	private String nom;
	private String dateDeNaissance;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private String adressePostale;
	private String telephone;
	public PatientDto() {
		super();
	}
	public PatientDto(String prenom, String nom, String dateDeNaissance, Genre genre, String adressePostale,
			String telephone) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.dateDeNaissance = dateDeNaissance;
		this.genre = genre;
		this.adressePostale = adressePostale;
		this.telephone = telephone;
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
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
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
