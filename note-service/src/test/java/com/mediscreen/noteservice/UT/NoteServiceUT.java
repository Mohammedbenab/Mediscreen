package com.mediscreen.noteservice.UT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.noteservice.dto.NoteDto;
import com.mediscreen.noteservice.entities.Note;
import com.mediscreen.noteservice.exception.NoneNotePatientException;
import com.mediscreen.noteservice.exception.NoteNotFoundException;
import com.mediscreen.noteservice.repository.NotesRepository;
import com.mediscreen.noteservice.service.NoteService;

@SpringBootTest
public class NoteServiceUT {
	
	@Autowired
	NoteService noteService;
	
	@MockBean
	NotesRepository notesRepository;
	
	Note note;
	NoteDto noteDto;
	
	@BeforeEach
	void setup() {
		note = new Note(1, "Mohammed", "Benab", "Rien à signaler", LocalDate.now(), LocalDate.now());
		note.setNoteId(1);
		noteDto = new NoteDto(1, "Rien à signaler");
	}
	
	@Test
	void testNoteAddWhenNoteExist() throws Exception {
		Mockito.when(notesRepository.save(any(Note.class))).thenReturn(note);
		Note addNote = noteService.add(noteDto); 
		verify(notesRepository, Mockito.times(1)).findAllByPatientId(any(Integer.class));
		verify(notesRepository,Mockito.times(1)).save(any(Note.class));
		assertEquals(noteDto.getPatientId(), addNote.getPatientId());
		assertNotNull(addNote);
	}

	@Test
	void testUpdateNote() throws NoteNotFoundException {
		Mockito.when(notesRepository.findById(any(Integer.class))).thenReturn(Optional.of(note));
		note.setNote("Note update");
		noteService.update(note, 1);
		verify(notesRepository,Mockito.times(1)).findById(any(Integer.class));
		verify(notesRepository,Mockito.times(1)).save(any(Note.class));
	}
	
	@Test
	void testDeleteNote() throws Exception {
		Mockito.when(notesRepository.findById(any(Integer.class))).thenReturn(Optional.of(note));
		noteService.delete(1);
		verify(notesRepository,Mockito.times(1)).delete(any(Note.class));
	}
	
	@Test
	void testGetByNoteId() throws NoteNotFoundException {
		Mockito.when(notesRepository.findById(any(Integer.class))).thenReturn(Optional.of(note));
		noteService.getById(1);
		verify(notesRepository,Mockito.times(1)).findById(any(Integer.class));
	}
	
	@Test
	void testGetListNoteByPatientId() throws NoneNotePatientException {
		List<Note> list = new ArrayList<>();
		list.add(note);
		Mockito.when(notesRepository.findAllByPatientId(any(Integer.class))).thenReturn(list);
		noteService.getNoteByPatient(1);
		verify(notesRepository,Mockito.times(1)).findAllByPatientId(any(Integer.class));
	}
}
