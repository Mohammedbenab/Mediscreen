package com.mediscreen.patientservice.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mediscreen.patientservice.entities.Genre;
import com.mediscreen.patientservice.entities.Patient;
import com.mediscreen.patientservice.entities.PatientDto;
import com.mediscreen.patientservice.exception.PatientNotFoundExeption;
import com.mediscreen.patientservice.repository.PatientRepository;
import com.mediscreen.patientservice.service.PatientService;

@SpringBootTest
public class PatientServiceIT {
	
	@Autowired
	private PatientRepository patientRepo;
	
	@Autowired
	private PatientService patientService;
	
	
	Patient patient;
	PatientDto patientDto;
	
	@AfterEach
	void clean() {
		this.patientRepo.deleteAll();	
		}
	
	@BeforeEach
	void setUp() throws Exception {
		patientDto = new PatientDto("Mohammed", "Benabda", "1990-09-22", Genre.M, "7, rue de Paris", "0654871254");
		patient = patientService.savePatient(patientDto);

	}
	
	
	@Test
	void testAddPatient() {
		assertEquals(1, patientRepo.findAll().size());
	}
	
	@Test
	void testFindAllPatients() {
		List<Patient> list = patientService.allPatient();
		assertEquals(1, list.size());
		assertEquals(list.size(), patientRepo.findAll().size());
	}
	
	@Test
	void testFindPatientById() throws PatientNotFoundExeption {
		Patient patientFind = patientService.findPatient(patient.getId());
		assertNotNull(patientFind);
	}
	
	@Test
	void testUpdatePatient() throws Exception{
		Patient patientBefore = patientService.findPatient(patient.getId());
		String beforeName = patientBefore.getPrenom();
		patientBefore.setPrenom("Mohammed el Amine");
		Patient patientAfter = patientService.updatePatient(patientBefore, patientBefore.getId());
		String afterName = patientAfter.getPrenom();
		assertNotEquals(beforeName, afterName);
		assertEquals(1, patientRepo.findAll().size());
	}
	
	@Test
	void testDeletePatientById() throws PatientNotFoundExeption {
		int before = patientRepo.findAll().size();
		patientService.deletePatient(patient.getId());
		int after = patientRepo.findAll().size();
		assertEquals(1, before);
		assertEquals(0, after);

	}
	

}
