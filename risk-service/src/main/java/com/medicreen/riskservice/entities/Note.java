package com.medicreen.riskservice.entities;

import java.time.LocalDate;

public class Note {
	private Integer noteId;
	private int patientId;
	private String patientPrenom;
	private String patientNom;
	private String note;
	private LocalDate dateCreation;
	private LocalDate dateModification;
	
	
	
	public Note() {
		super();
	}



	public Note(Integer noteId, int patientId, String patientPrenom, String patientNom, String note,
			LocalDate dateCreation, LocalDate dateModification) {
		super();
		this.noteId = noteId;
		this.patientId = patientId;
		this.patientPrenom = patientPrenom;
		this.patientNom = patientNom;
		this.note = note;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
	}



	public Integer getNoteId() {
		return noteId;
	}



	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}



	public int getPatientId() {
		return patientId;
	}



	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}



	public String getPatientPrenom() {
		return patientPrenom;
	}



	public void setPatientPrenom(String patientPrenom) {
		this.patientPrenom = patientPrenom;
	}



	public String getPatientNom() {
		return patientNom;
	}



	public void setPatientNom(String patientNom) {
		this.patientNom = patientNom;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public LocalDate getDateCreation() {
		return dateCreation;
	}



	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}



	public LocalDate getDateModification() {
		return dateModification;
	}



	public void setDateModification(LocalDate dateModification) {
		this.dateModification = dateModification;
	}
	
	
}
