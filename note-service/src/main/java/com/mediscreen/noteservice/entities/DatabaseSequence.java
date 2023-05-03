package com.mediscreen.noteservice.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Database_sequences")
public class DatabaseSequence {
	
	@Id
	private String id;
	
	private int seq;

	
	public DatabaseSequence() {
		super();
	}


	public DatabaseSequence(String id, Integer seq) {
		super();
		this.id = id;
		this.seq = seq;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getSeq() {
		return seq;
	}


	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	

}
