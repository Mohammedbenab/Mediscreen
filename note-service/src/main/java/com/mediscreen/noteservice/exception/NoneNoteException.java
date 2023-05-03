package com.mediscreen.noteservice.exception;

public class NoneNoteException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoneNoteException() {
		super("None notes found");
	}
	
	

}
