package com.medicreen.riskservice.entities;

public class ResultPatientRisk {

	private String prenom;
	private String nom;
	private int age;
	private String result;
	
	public ResultPatientRisk() {
	}

	public ResultPatientRisk(String prenom, String nom, int age, String result) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
		this.result = result;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
