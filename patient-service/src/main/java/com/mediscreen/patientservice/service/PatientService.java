package com.mediscreen.patientservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.patientservice.entities.Patient;
import com.mediscreen.patientservice.entities.PatientDto;
import com.mediscreen.patientservice.entities.Response;
import com.mediscreen.patientservice.exception.PatientNotFoundExeption;
import com.mediscreen.patientservice.repository.PatientRepository;

@Service
public class PatientService {
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(PatientService.class);

	
	@Autowired
	private PatientRepository patientRepository;
	
	
	public Patient savePatient(PatientDto patientDto) throws Exception {
		Patient patient = patientRepository.findByPrenomAndNom(patientDto.getPrenom(), patientDto.getNom());
		if (patient == null) {
			Patient newPatient = new Patient();
			newPatient.setPrenom(patientDto.getPrenom());
			newPatient.setNom(patientDto.getNom());
			newPatient.setAdressePostale(patientDto.getAdressePostale());
			newPatient.setDateDeNaissance(patientDto.getDateDeNaissance());
			newPatient.setGenre(patientDto.getGenre());
			newPatient.setTelephone(patientDto.getTelephone());
			patientRepository.save(newPatient);
			logger.info("Patient saved successsfully");
			return newPatient;
		}else
			 throw new PatientNotFoundExeption();
			
	}
	
	public Patient updatePatient(Patient patient, Integer id) throws Exception {
		Patient patientUp = patientRepository.findById(id).orElse(null);
		if (patientUp != null) {
			patientUp.setAdressePostale(patient.getAdressePostale());
			patientUp.setDateDeNaissance(patient.getDateDeNaissance());
			patientUp.setGenre(patient.getGenre());
			patientUp.setNom(patient.getNom());
			patientUp.setPrenom(patient.getPrenom());
			patientUp.setTelephone(patient.getTelephone());
			patientRepository.save(patientUp);
			logger.info("Patient updated successsfully");
			return patientUp;
		}else
			throw new PatientNotFoundExeption();
	}
	
	public Response deletePatient(Integer id) throws PatientNotFoundExeption {
		Patient patient = patientRepository.findById(id).orElse(null);
		Response response = new Response();
		if (patient != null) {
			patientRepository.delete(patient);
			logger.info("Patient deleted successsfully");
			response.setReponse("Patient deleted");
			return response;
		}else
			 throw new PatientNotFoundExeption();

	}
	
	public Patient findPatient(Integer id) throws PatientNotFoundExeption {
		Patient patient = patientRepository.findById(id).orElse(null);
		if (patient != null) {
			logger.info("Patient find successsfully");
			return patient;
		}else
			 throw new PatientNotFoundExeption();
	}
	
	public List<Patient> allPatient(){
		return patientRepository.findAll();
	}

}
