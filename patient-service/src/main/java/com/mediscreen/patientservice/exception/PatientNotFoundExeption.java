package com.mediscreen.patientservice.exception;

public class PatientNotFoundExeption extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PatientNotFoundExeption() {
		super("Patient not found");
	}

}
