package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class Owasp10NotSecuredApplication {

	public static void main(String[] args) {
		SpringApplication.run(Owasp10NotSecuredApplication.class, args);
	}

}
