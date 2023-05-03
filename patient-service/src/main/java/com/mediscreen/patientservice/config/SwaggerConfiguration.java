package com.mediscreen.patientservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public OpenAPI baseOpenApi() {
		return new OpenAPI().info(new Info()
				.title("Patient REST API")
				.version("1.0")
				.description("Documentation de l'api patient de la clinique Abernathy")
			);
	}
}
