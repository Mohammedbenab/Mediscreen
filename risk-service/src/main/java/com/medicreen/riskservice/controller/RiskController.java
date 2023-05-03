package com.medicreen.riskservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicreen.riskservice.entities.ResultPatientRisk;
import com.medicreen.riskservice.services.CalculRisk;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/risk")
public class RiskController {

	@Autowired
	private CalculRisk calculRisk;
	
	@GetMapping("/{patientId}")
	@Operation(
			description = "Obtenir le risque de diabete du patient",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Risk patient",
							content = {@Content(mediaType = "application/json",
										schema =@Schema(implementation = ResultPatientRisk.class)
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
	public ResponseEntity<ResultPatientRisk> calculRiskByPatientId(@PathVariable Integer patientId) throws Exception{
		ResultPatientRisk resultCalcul = calculRisk.calculLevelRisk(patientId);
		if(resultCalcul != null) {
			
			return new ResponseEntity<ResultPatientRisk>(resultCalcul, HttpStatus.OK);
		}else
			return new ResponseEntity<ResultPatientRisk>(resultCalcul, HttpStatus.NOT_FOUND);
		
	}
	
	
}
