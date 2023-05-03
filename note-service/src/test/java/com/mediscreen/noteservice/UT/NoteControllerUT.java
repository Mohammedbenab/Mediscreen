package com.mediscreen.noteservice.UT;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.noteservice.dto.NoteDto;
import com.mediscreen.noteservice.entities.Note;
import com.mediscreen.noteservice.entities.Response;
import com.mediscreen.noteservice.exception.NoneNotePatientException;
import com.mediscreen.noteservice.service.NoteService;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerUT {
	

	@MockBean
	NoteService noteService;
	
	@Autowired
	MockMvc mock;
	
	@Autowired
	ObjectMapper mapper;

	
	Note note;
	NoteDto noteDto;
	
	@BeforeEach
	void setup() {
		note = new Note(1, "Mohammed", "Benab", "Rien à signaler", LocalDate.now(), LocalDate.now());
		note.setNoteId(1);
		noteDto = new NoteDto(1, "Rien à signaler");
	}
	
	@Test
	void testAddController() throws JsonProcessingException, Exception {
		note.setNoteId(1);
		Mockito.when(noteService.add(ArgumentMatchers.any())).thenReturn(note);
		mock.perform(post("/api/notes/add")
		.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(mapper.writeValueAsString(noteDto)))
		.andExpect(status().isCreated())
	    .andExpect(jsonPath("$.noteId", Matchers.equalTo(1)));
	}
	
	@Test
	void testDeleteController() throws Exception {
		Response response = new Response("Note deleted");
		Mockito.when(noteService.delete(any(Integer.class))).thenReturn(response);
		mock.perform(delete("/api/notes/delete/1"))	
			.andExpect(status().isOk());
	}
	
	@Test
	void testUpdateController() throws JsonProcessingException, Exception {
		Note updateN =  new Note(1, "Mohammed", "Benab", "Problem detected", LocalDate.now(), LocalDate.now());
		Mockito.when(noteService.update(ArgumentMatchers.any(),any(Integer.class))).thenReturn(updateN);
		mock.perform(put("/api/notes/update/1")
		.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(mapper.writeValueAsString(updateN)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.note", Matchers.is("Problem detected")));
	}
	
	@Test
	void testListByPatientIdController() throws Exception {
		List<Note> list = new ArrayList<>();
		list.add(note);
		System.out.println(note.getNoteId());
		Mockito.when(noteService.getNoteByPatient(any(Integer.class))).thenReturn(list);
		
		mock.perform(get("/api/notes/list/patient/1"))
		 .andExpect(status().isOk())
	        .andExpect(jsonPath("$", Matchers.hasSize(1)))
	        .andExpect(jsonPath("$[0].patientPrenom", Matchers.is("Mohammed")));
	}
	
	@Test
	void testNoteByIdController() throws Exception {
		Mockito.when(noteService.getById(1)).thenReturn(note);
		
		mock.perform(get("/api/notes/detail/1"))	
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.patientId", Matchers.is(1)));
		
	}
}
