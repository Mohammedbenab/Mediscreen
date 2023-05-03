package com.mediscreen.noteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mediscreen.noteservice.dto.NoteDto;
import com.mediscreen.noteservice.entities.Note;
import com.mediscreen.noteservice.entities.Response;
import com.mediscreen.noteservice.exception.NoneNotePatientException;
import com.mediscreen.noteservice.exception.NoteNotFoundException;
import com.mediscreen.noteservice.service.NoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notes")
public class NotesController {
	
	@Autowired
	private NoteService noteService;
	
	@PostMapping("/add")
	@Operation(
			description = "Permet d'ajouter une nouvelle note",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "note ajouté avec succès",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = NoteDto.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "note not found",
							content = @Content(mediaType = "application/json"
								)
					),
					@ApiResponse(
							responseCode = "409",
							description = "note exist",
							content = @Content(mediaType = "application/json"
								)
					)
			}
	)
	public ResponseEntity<Note> creatNote(@RequestBody NoteDto noteDto) throws Exception {
		Note note = new Note();
			note = noteService.add(noteDto);
			if(note != null) {
				return new ResponseEntity<Note>(note,HttpStatus.CREATED);		
			}else
				return new ResponseEntity<Note>(note,HttpStatus.NOT_FOUND);		
		}
	
	
	@DeleteMapping("/delete/{id}")
	@Operation(
			description = "Permet de supprimer une note",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "note deleted",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Note.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Patient not found",
							content = @Content(mediaType = "application/json"
							)
					),
					
			}
	)
	public ResponseEntity<Response>  deleteNote(@PathVariable int id) throws Exception {
		Response result =noteService.delete(id);
		if(result != null) {
			return new ResponseEntity<Response>(result,HttpStatus.OK);
		}else
			return new ResponseEntity<Response>(result,HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/update/{id}")
	@Operation(
			description = "Permet de modifier une note",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "note modifiée avec succès",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Note.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "note not found",
							content = @Content(mediaType = "application/json"
								)
					),
					
			}
	)
	public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody Note note) throws NoteNotFoundException {
			Note noteUp = noteService.update(note,id);
			if(noteUp != null) {
				return new ResponseEntity<Note>(note,HttpStatus.OK);		
			}else
				return new ResponseEntity<Note>(note,HttpStatus.NOT_FOUND);		

	}
	
	@GetMapping("/list/patient/{id}")
	@Operation(
			description = "Obtenir la liste des note par patient",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "La liste des notes par patient",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Note.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Not found",
							content = @Content(mediaType = "application/json"
								)
					)
			}
	)
	public ResponseEntity<List<Note>> getAllNotesPatientId(@PathVariable int id) throws NoneNotePatientException {
			List<Note> noteList= noteService.getNoteByPatient(id);
			if(noteList.size() > 0 ) {
				return new ResponseEntity<List<Note>>(noteList,HttpStatus.OK);
			}else
				return new ResponseEntity<List<Note>>(noteList,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/detail/{id}")
	@Operation(
			description = "Obtenir des informations sur une note",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Informations note",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Note.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "note not found",
							content = @Content(mediaType = "application/json"
								)
					)
			}
	)
	public ResponseEntity<Note> getNoteById(@PathVariable Integer id) throws NoteNotFoundException {
		Note note = noteService.getById(id);
		if(note != null) {
			return new ResponseEntity<Note>(note,HttpStatus.OK);
		}else
			return new ResponseEntity<Note>(note,HttpStatus.NOT_FOUND);
	}

}
