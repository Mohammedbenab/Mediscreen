package com.mediscreen.noteservice.dto;

public class NoteDto {

	private int patientId;
	private String note;
	public NoteDto() {
	}
	public NoteDto(int patientId, String note) {
		this.patientId = patientId;
		this.note = note;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
