package com.mediscreen.patientservice.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String prenom;
	private String nom;
	private String dateDeNaissance;
	@Enumerated(EnumType.STRING)
	private Genre genre;
	private String adressePostale;
	private String telephone;
	
	
	public Patient() {
	}


	public Patient(String prenom, String nom, String dateDeNaissance, Genre genre, String adressePostale,
			String telephone) {
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
