package com.mediscreen.patientservice.controller;

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
import org.springframework.web.server.ResponseStatusException;

import com.mediscreen.patientservice.entities.Patient;
import com.mediscreen.patientservice.entities.PatientDto;
import com.mediscreen.patientservice.entities.Response;
import com.mediscreen.patientservice.exception.PatientNotFoundExeption;
import com.mediscreen.patientservice.service.PatientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/patients")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
		
	@GetMapping("/list")
	@Operation(
			description = "Obtenir la liste des patients",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "La liste des patients",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Patient.class)
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
	public ResponseEntity<List<Patient>> patientList() {
		List<Patient> list = patientService.allPatient();
		if(list.size() > 0) {
			return new ResponseEntity<List<Patient>>(list, HttpStatus.OK);
		}else
		return new ResponseEntity<List<Patient>>(list, HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/add")
	@Operation(
			description = "Permet d'ajouter un nouveau patient",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "patient ajouté avec succès",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = PatientDto.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Patient not found",
							content = @Content(mediaType = "application/json"
								)
					),
					@ApiResponse(
							responseCode = "409",
							description = "Patient exist",
							content = @Content(mediaType = "application/json"
								)
					)
			}
	)
	 public ResponseEntity<Patient> addPatient(@RequestBody PatientDto patientDto) throws Exception {
		Patient patient = patientService.savePatient(patientDto);
		if(patient != null) {
			return new ResponseEntity<>(patient, HttpStatus.CREATED);
		}else
        return new ResponseEntity<>(patient, HttpStatus.CONFLICT);
    }

    @PutMapping("/update/{id}")
    @Operation(
			description = "Permet de modifier les informations d'un patient",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "informations modifier avec succès",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Patient.class)
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
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient patient) throws Exception {
		Patient patientUp = patientService.updatePatient(patient, id);
		if(patientUp != null) {
	        return new ResponseEntity<>(patientUp, HttpStatus.OK);
		}else
        return new ResponseEntity<>(patientUp, HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/delete/{id}")
    @Operation(
			description = "Permet de supprimer un patient",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Patient deleted",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Patient.class)
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
    public ResponseEntity<Response> deletePatient(@PathVariable("id") Integer id) throws PatientNotFoundExeption{
    	Response response = new Response();
    	try {
    		response = patientService.deletePatient(id);
    	}catch(Exception ex) {
    		throw new ResponseStatusException(
    		           HttpStatus.NOT_FOUND, ex.getMessage());
    	}
		return new ResponseEntity<Response>(response, HttpStatus.OK);

    }
    
    @GetMapping("/detail/{id}")
    @Operation(
			description = "Obtenir des informations sur un patient",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Informations patient",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = Patient.class)
									)}
					),
					@ApiResponse(
							responseCode = "404",
							description = "Patient not found",
							content = @Content(mediaType = "application/json"
								)
					)
			}
	)
    public ResponseEntity<?> getPatientById(@PathVariable("id") Integer id) throws PatientNotFoundExeption {
    	Patient patient = patientService.findPatient(id);
    	Response response = new Response("Aucune information trouvee");
    	if(patient != null) {
    		 return new ResponseEntity<>(patient, HttpStatus.OK);
    	}else
    		 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    	
    }	
    
}
