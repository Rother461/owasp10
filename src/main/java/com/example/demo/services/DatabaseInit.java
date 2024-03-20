package com.example.demo.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInit {

    @Autowired
    private EntityManager entityManager;


    @Transactional
    @Modifying
    public void initializeDatabase() {
        String query = createUsers();
        entityManager.createNativeQuery(query).executeUpdate();
    }

    private String createUsers() {
        return "CREATE TABLE IF NOT EXISTS users(" +
                "`id` INT AUTO_INCREMENT, " +
                "`username` VARCHAR(250) NOT NULL UNIQUE, " +
                "`password` VARCHAR(250) NOT NULL, " +
                "`client_id` VARCHAR(250) NOT NULL, " +
                "`client_secret` VARCHAR(250) NOT NULL, " +
                "`createdAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "`updatedAt` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, " +
                "PRIMARY KEY(`id`));\n";
    }
}
