package com.medicreen.riskservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medicreen.riskservice.entities.Patient;

@FeignClient(name = "microservice-patient", url = "${patient.url.cross}")
public interface PatientsProxy {
	
	@GetMapping("/detail/{id}")
	public Patient getPatientById(@PathVariable Integer id);

}
