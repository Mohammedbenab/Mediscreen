package com.mediscreen.noteservice.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.noteservice.dto.NoteDto;
import com.mediscreen.noteservice.entities.Note;
import com.mediscreen.noteservice.entities.Response;
import com.mediscreen.noteservice.exception.NoneNoteException;
import com.mediscreen.noteservice.exception.NoneNotePatientException;
import com.mediscreen.noteservice.exception.NoteNotFoundException;
import com.mediscreen.noteservice.repository.NotesRepository;

@Service
public class NoteService {
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(NoteService.class);

	
	@Autowired
	private NotesRepository notesRepository;
	
	@Autowired
	private SequenceGeneratorService seGeneratorService;
	
	public Note add(NoteDto noteDto) throws Exception {
		List<Note> findList = notesRepository.findAllByPatientId(noteDto.getPatientId());
		boolean isTrue = findList.stream().anyMatch(res -> res.getNote().contains(noteDto.getNote()));
		Note note = new Note();
		if(!isTrue) {
			note.setNoteId(seGeneratorService.generateSequence(Note.SEQUENCE_NAME));
			note.setPatientId(noteDto.getPatientId());
			note.setNote(noteDto.getNote());
			note.setDateCreation(LocalDate.now());
			note.setDateModification(LocalDate.now());
			notesRepository.save(note);
			logger.info("Note saved successsfully");
			return note;
		}else
			 throw new Exception("Notes exist in patient list ");
	}
	
	public Note update(Note note, Integer id) throws NoteNotFoundException {
		Note findNote = notesRepository.findById(id).orElse(null);
		if(findNote != null) {
			findNote.setNote(note.getNote());
			findNote.setDateModification(LocalDate.now());
			notesRepository.save(findNote);
			logger.info("Note updated successsfully");
			return findNote;
		}else
			throw new NoteNotFoundException(note.getPatientId());
	}
	
	public Response delete(Integer id) throws Exception {
		Note findNote = notesRepository.findById(id).orElse(null);
		Response response = new Response();
		if(findNote != null) {
			notesRepository.delete(findNote);
			logger.info("Note deleted successsfully");
			response.setResponse("Note deleted");
			return response;
		}else
			 throw new Exception("Note not found");
	}
	
	public Note getById(Integer id) throws NoteNotFoundException {
		Note note = notesRepository.findById(id).orElse(null);
		if (note != null) {
			return note;
		}else 
			throw new NoteNotFoundException(id);
	}
	
	public List<Note> getAllNote() throws NoneNoteException{
		List<Note> listNote = notesRepository.findAll();
		if(listNote.size() > 0) {
			return listNote;
		}else
			throw new NoneNoteException();
	}

	public List<Note> getNoteByPatient(int patientId) throws NoneNotePatientException{
		List<Note> patientNoteList = notesRepository.findAllByPatientId(patientId);
		if (patientNoteList.size() > 0) {
			return patientNoteList;
		}else 
			throw new NoneNotePatientException(patientId);	
		}
}
