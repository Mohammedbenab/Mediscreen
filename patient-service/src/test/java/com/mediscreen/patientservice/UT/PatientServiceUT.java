package com.mediscreen.patientservice.UT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mediscreen.patientservice.entities.Genre;
import com.mediscreen.patientservice.entities.Patient;
import com.mediscreen.patientservice.entities.PatientDto;
import com.mediscreen.patientservice.exception.PatientNotFoundExeption;
import com.mediscreen.patientservice.repository.PatientRepository;
import com.mediscreen.patientservice.service.PatientService;


@SpringBootTest
public class PatientServiceUT {

	@Autowired
	PatientService patientService;
	
	@MockBean
	PatientRepository patientRepository;
	
	Patient patient;
	PatientDto patientDto;
	
	@BeforeEach
	void setup() {
		patient = new Patient("Mohammed", "Benabda", "1990-09-22", Genre.M, "7, rue de Paris", "0654871254");
		patientDto = new PatientDto("Mohammed", "Benabda", "1990-09-22", Genre.M, "7, rue de Paris", "0654871254");
	}
	
	@Test
	void testSavePatientNotExist() throws Exception {
		Mockito.when(patientRepository.save(any(Patient.class))).thenReturn(patient);
		Patient patientCreated = patientService.savePatient(patientDto);
		verify(patientRepository, Mockito.times(1)).findByPrenomAndNom(patient.getPrenom(), patient.getNom());
		assertEquals(patient.getNom(), patientCreated.getNom());
	}
	
	@Test
	void testUpdatePatient() throws Exception {
		patient.setId(1);
		Mockito.when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
		patient.setNom("Benabdallah");
		patientService.updatePatient(patient,1);
		verify(patientRepository, Mockito.times(1)).save(patient);
		verify(patientRepository, Mockito.times(1)).findById(1);
	}
	
	@Test
	void testDeletePatient() throws PatientNotFoundExeption{
		patient.setId(1);
		Mockito.when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
		patientService.deletePatient(1);
		verify(patientRepository, Mockito.times(1)).delete(patient);
		
	}
	
	@Test 
	void testGetPatientById() throws PatientNotFoundExeption {
		patient.setId(1);
		Mockito.when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
		patientService.findPatient(1);
		verify(patientRepository, Mockito.times(1)).findById(1);
	}
	
	@Test
	void testGetAllPatient() {
		patient.setId(1);
		Patient patient1 = new Patient("Lora", "Morreau", "1991-10-30", Genre.F, "7, rue de Paris", "0654452154");
		patient1.setId(2);
		List<Patient> list = new ArrayList<>();
		list.add(patient);
		list.add(patient1);
		Mockito.when(patientRepository.findAll()).thenReturn(list);
		patientService.allPatient();
		verify(patientRepository, Mockito.times(1)).findAll();
	}
	
}
