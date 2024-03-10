package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void register(String username, String password) {
        String query = "INSERT INTO users(username,password) VALUES ('" + username + "','" + password + "')";
        jdbcTemplate.execute(query);
    }

    public User login(String username, String password) {

        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
        try {
            return jdbcTemplate.query(query, rs -> {
                if (rs.next()) {
                    User user = new User(rs.getString("username"),rs.getString("password"));
                    return user;
                }
                return null;
            });
        } catch (Exception e) {
            return null;
        }
    }
}
