package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "`users`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String client_id;
    private String client_secret;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.client_id = UUID.randomUUID().toString();
        this.client_secret = UUID.randomUUID().toString();
    }
}