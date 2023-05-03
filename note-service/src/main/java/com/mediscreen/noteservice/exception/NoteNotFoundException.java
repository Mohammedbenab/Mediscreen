package com.mediscreen.noteservice.exception;

public class NoteNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoteNotFoundException(int noteId) {
		super("Notes for patient id "+noteId+" isn't found");
	}
	
	
}
