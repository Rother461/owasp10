package com.example.demo;

import com.example.demo.services.DatabaseInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RequestMapping("/api/v3")
public class Owasp10NotSecuredApplication {

	@Autowired
	private DatabaseInit databaseInit;

	public static void main(String[] args) {

		SpringApplication.run(Owasp10NotSecuredApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationStart() {
		databaseInit.initializeDatabase();
	}
}
