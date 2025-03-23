package org.arai;

import org.arai.Entities.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.arai")
public class AraiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AraiBackendApplication.class, args); 
		
	}

}
