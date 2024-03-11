package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/api/v3")
public class Owasp10NotSecuredApplication {

	public static void main(String[] args) {
		SpringApplication.run(Owasp10NotSecuredApplication.class, args);
	}

}
