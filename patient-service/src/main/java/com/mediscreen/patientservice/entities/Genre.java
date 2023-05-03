package com.mediscreen.patientservice.entities;

public enum Genre {
	M ("Male"),
	F ("Female");
	
	private final String name;

	private Genre(String name) {
		this.name = name;
	}
	
	
}
