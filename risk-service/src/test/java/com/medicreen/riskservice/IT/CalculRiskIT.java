package com.medicreen.riskservice.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicreen.riskservice.entities.ResultPatientRisk;
import com.medicreen.riskservice.services.CalculRisk;

@SpringBootTest
public class CalculRiskIT {

	@Autowired
	CalculRisk calculRisk;
	
	ResultPatientRisk result;
	
	@Test
	void calculRiskWhenPatientExist() throws Exception {
		ResultPatientRisk result = calculRisk.calculLevelRisk(39);
		assertNotNull(result);
		assertEquals("None", result.getResult());
	}
}
