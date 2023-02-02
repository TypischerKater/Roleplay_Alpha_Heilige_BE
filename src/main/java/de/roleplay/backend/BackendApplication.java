package de.roleplay.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		mapGenerator.generateAndPrintMap();//here for test reasons
		SpringApplication.run(BackendApplication.class, args);
	}

}
