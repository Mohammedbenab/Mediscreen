package com.medicreen.riskservice.UT;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.medicreen.riskservice.entities.Note;
import com.medicreen.riskservice.entities.Patient;
import com.medicreen.riskservice.proxy.NotesProxy;
import com.medicreen.riskservice.proxy.PatientsProxy;
import com.medicreen.riskservice.services.CalculRisk;

@SpringBootTest
public class RiskServiceUT {
	
	@Autowired
	CalculRisk calculRisk;
	
	@MockBean
	PatientsProxy patientsProxy;
	
	@MockBean
	NotesProxy notesProxy;
	
	Patient patient;
	Note note;
	
	@BeforeEach
	void setUp() {
		patient = new Patient(1,"Mohammed", "Benabda", "1990-09-22", "H", "7, rue de Paris", "0654871254");
		note = new Note(1,1, "Mohammed", "Benab", "Rien à signaler", LocalDate.now(), LocalDate.now());
	}
	
	@Test
	void calculRiskTest() throws Exception {
		List<Note> list = new ArrayList<>();
		list.add(note);
		Mockito.when((patientsProxy.getPatientById(any(Integer.class)))).thenReturn(patient);
		Mockito.when(notesProxy.getAllNotesPatient(any(Integer.class))).thenReturn(list);
		calculRisk.calculLevelRisk(1);
		verify(notesProxy,Mockito.times(1)).getAllNotesPatient(any(Integer.class));
		verify(notesProxy,Mockito.times(1)).getAllNotesPatient(any(Integer.class));

	}
	
}
