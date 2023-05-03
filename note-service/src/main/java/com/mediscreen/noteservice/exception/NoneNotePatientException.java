package com.mediscreen.noteservice.exception;

public class NoneNotePatientException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoneNotePatientException(int id) {
		super("None notes list found by patient id : "+id);
	}
	
	

}
