package com.example.demo.controllers;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class Loginowanie {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserRegisterDto registrationDto) {
        userService.registerUser(registrationDto.getUsername(), registrationDto.getPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fetch-data")
    public String fetchData(@RequestParam("url") String url) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String content = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            JSONObject json = XML.toJSONObject(content);
            System.out.println(json.toString(4));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
