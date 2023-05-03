package com.mediscreen.patientservice.UT;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mediscreen.patientservice.entities.Genre;
import com.mediscreen.patientservice.entities.Patient;
import com.mediscreen.patientservice.entities.PatientDto;
import com.mediscreen.patientservice.entities.Response;
import com.mediscreen.patientservice.service.PatientService;

@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerUT {
	
	@Autowired
	MockMvc mock;
	
	@MockBean
	PatientService patientService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	Patient patient;
	PatientDto patientDto;
	
	@BeforeEach
	void setUp() {
		patient = new Patient("Mohammed", "Benabda", "1990-09-22", Genre.M, "7, rue de Paris", "0654871254");
		patient.setId(1);
		patientDto = new PatientDto("Mohammed", "Benabda", "1990-09-22", Genre.M, "7, rue de Paris", "0654871254");
	}
	
	@Test
	void testFindAllPatient() throws Exception {
		Patient patient1 = new Patient("Laura", "Morreau", "1991-10-30", Genre.F, "7, rue de Paris", "0654452154");
		patient1.setId(2);
		List<Patient> list = new ArrayList<>();
		list.add(patient);
		list.add(patient1);
		Mockito.when(patientService.allPatient()).thenReturn(list);
		
		mock.perform(get("/api/patients/list"))
		 .andExpect(status().isOk())
	        .andExpect(jsonPath("$", Matchers.hasSize(2)))
	        .andExpect(jsonPath("$[0].prenom", Matchers.is("Mohammed")));
	}
	
	@Test
	void testPatientById() throws Exception {
		Mockito.when(patientService.findPatient(1)).thenReturn(patient);
		
		mock.perform(get("/api/patients/detail/1"))	
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.prenom", Matchers.is("Mohammed")));
	}
	
	@Test
	void testDeletePatientById() throws Exception {
		Response response = new Response("Patient deleted");
		Mockito.when(patientService.deletePatient(any(Integer.class))).thenReturn(response);
		mock.perform(delete("/api/patients/delete/1"))	
			.andExpect(status().isOk());
	}
	
	@Test
	void testPatientUpdate() throws Exception {
		Patient updateP = new Patient("Mohammed", "Benabda", "1990-09-22", Genre.M, "7, rue de Paris", "0654871254");
		updateP.setId(1);
		Mockito.when(patientService.updatePatient(ArgumentMatchers.any(),any(Integer.class))).thenReturn(updateP);
		mock.perform(put("/api/patients/update/1")
		.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(mapper.writeValueAsString(updateP)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.prenom", Matchers.is("Mohammed")));
	}
	
	@Test
	void testAddPatient() throws Exception {
		Mockito.when(patientService.savePatient(ArgumentMatchers.any())).thenReturn(patient);
		mock.perform(post("/api/patients/add")
		.contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
		.content(mapper.writeValueAsString(patientDto)))
		.andExpect(status().isCreated())
	    .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
	}

}
