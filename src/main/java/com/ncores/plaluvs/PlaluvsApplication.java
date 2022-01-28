package com.ncores.plaluvs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class PlaluvsApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlaluvsApplication.class, args);
	}
}
