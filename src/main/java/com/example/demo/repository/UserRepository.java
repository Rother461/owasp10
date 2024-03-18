package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(String username, String password, String clientId, String clientSecret) {
        String query = "INSERT INTO users(username, password, client_id, client_secret) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, username, password, clientId, clientSecret);
    }

    public User login(String username, String password) {

        String usernameQuery = "SELECT * FROM users WHERE username = ?";

        try {
            User user = jdbcTemplate.queryForObject(usernameQuery, new Object[]{username}, (rs, rowNum) ->
                    new User(rs.getString("username"), rs.getString("password"), rs.getString("client_id"), rs.getString("client_secret"))
            );

            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {

                throw new UsernameNotFoundException("Invalid username or password");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("User not found with provided username");
        }
    }

}

