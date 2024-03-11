package com.example.demo.services;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username,encodedPassword);
        userRepository.register(username,encodedPassword,user.getClient_id(),user.getClient_secret());
        return user;
    }
}
