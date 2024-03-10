package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class Loginowanie {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public void register(@RequestParam("username") String username, @RequestParam("password") String password) {
        userRepository.register(username, password);
    }

    @GetMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userRepository.login(username, password);
        System.out.println(user.getUsername());
    }
}
