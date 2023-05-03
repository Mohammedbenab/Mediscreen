package com.mediscreen.patientservice.exception;

public class PatientExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PatientExistException(String name) {
		super("Patient by name "+name+" exist");
	}

}
