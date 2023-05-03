package com.mediscreen.noteservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI baseOpenApi() {
		return new OpenAPI().info(new Info()
				.title("Note REST API")
				.version("1.0")
				.description("Documentation de l'api note de la clinique Abernathy")
			);
	}

}
