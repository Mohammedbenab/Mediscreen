package com.medicreen.riskservice.UT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.medicreen.riskservice.entities.Note;
import com.medicreen.riskservice.entities.Patient;
import com.medicreen.riskservice.entities.ResultPatientRisk;
import com.medicreen.riskservice.services.CalculRisk;

@SpringBootTest
@AutoConfigureMockMvc
public class RiskControllerUT {
	
	@Autowired
	MockMvc mock;
	
	@MockBean
	CalculRisk calculRisk;
		
	Patient patient;
	Note note;
	ResultPatientRisk result;
	
	@BeforeEach
	void setUp() {
		patient = new Patient(1,"Mohammed", "Benabdallah", "1990-09-22", "H", "7, rue de Paris", "0654871254");
		note = new Note(1,1, "Mohammed", "Benabdallah", "Rien à signaler", LocalDate.now(), LocalDate.now());
		result = new ResultPatientRisk("Mohammed","Benabdallah", 32, "None");
	}
	
	@Test
	void testCalculRiskWhenPatientExist() throws Exception{
		Mockito.when(calculRisk.calculLevelRisk(1)).thenReturn(result);
				
		mock.perform(get("/api/risk/1"))	
		.andExpect(status().isOk())
        .andExpect(jsonPath("$.prenom", Matchers.is("Mohammed")))
        .andExpect(jsonPath("$.result", Matchers.is("None")));

	}

}
