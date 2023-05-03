package com.medicreen.riskservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medicreen.riskservice.entities.Note;

@FeignClient(name="microservice-note", url="${notes.url.cross}")
public interface NotesProxy {

	@GetMapping("/list/patient/{id}")
	public List<Note> getAllNotesPatient(@PathVariable Integer id);
		
}
