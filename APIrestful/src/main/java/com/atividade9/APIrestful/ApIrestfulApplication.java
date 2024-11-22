package com.atividade9.APIrestful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ApIrestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApIrestfulApplication.class, args);
	}

	// Configuração global de CORS
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Permite todos os endpoints
						.allowedOrigins("http://localhost:3000") // Permite apenas o frontend rodando nessa origem
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
						.allowedHeaders("*"); // Permite todos os cabeçalhos
			}
		};
	}
}
