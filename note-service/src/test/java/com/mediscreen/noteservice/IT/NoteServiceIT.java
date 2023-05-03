package com.mediscreen.noteservice.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mediscreen.noteservice.dto.NoteDto;
import com.mediscreen.noteservice.entities.Note;
import com.mediscreen.noteservice.exception.NoneNotePatientException;
import com.mediscreen.noteservice.exception.NoteNotFoundException;
import com.mediscreen.noteservice.repository.NotesRepository;
import com.mediscreen.noteservice.service.NoteService;

@SpringBootTest
public class NoteServiceIT {
	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private NoteService noteService;
	
	Note note;
	NoteDto noteDto;
	
	@AfterEach
	void clean() {
		notesRepository.deleteAll();
	}

	@BeforeEach
	void setUp() {
		noteDto = new NoteDto(1, "Rien à signaler");
	}
	
	@Test
	void addNote() throws Exception {
		note = noteService.add(noteDto);
		assertEquals(1, notesRepository.findAll().size());
	}
	
	@Test
	void updateNote() throws Exception {
		note = noteService.add(noteDto);
		String notebefore = note.getNote();
		note.setNote("Note updated");
		Note update = noteService.update(note,note.getNoteId());
		assertEquals(1, notesRepository.findAll().size());
		assertNotEquals(notebefore, update.getNote());
		
	}
	
	@Test
	void deleteNote() throws Exception {
		note = noteService.add(noteDto);
		Integer sizeBefore = notesRepository.findAll().size();
		noteService.delete(note.getNoteId());
		assertNotEquals(sizeBefore, notesRepository.findAll().size());
	}
	
	@Test
	void noteById() throws Exception {
		note = noteService.add(noteDto);
		Note noteFound = noteService.getById(note.getNoteId());
		assertEquals(1, notesRepository.findAll().size());
		assertNotNull(noteFound);
	}
	
	@Test
	void notesByPatientId() throws Exception {
		note = noteService.add(noteDto);
		List<Note> list = noteService.getNoteByPatient(note.getPatientId());
		assertEquals(1, notesRepository.findAll().size());
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getPatientId());

	}
}
