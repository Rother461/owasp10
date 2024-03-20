package com.example.demo.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

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
    @Nullable
    private String password;
    private String client_id;
    private String client_secret;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.client_id = UUID.randomUUID().toString();
        this.client_secret = UUID.randomUUID().toString();
    }

    public User(String username, @Nullable String password, String client_id, String client_secret) {
        this.username = username;
        this.password = password;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }
}