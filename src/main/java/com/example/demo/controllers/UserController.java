package com.example.demo.controllers;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entities.ErrorRes;
import com.example.demo.entities.Pracownik;
import com.example.demo.exceptions.UserRegistrationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Validated UserRegisterDto registrationDto) {
        try {
            userService.registerUser(registrationDto.getUsername(), registrationDto.getPassword());
            return ResponseEntity.ok().body("Użytkownik zarejetrowany poprawnie");
        } catch (UserRegistrationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Wystąpił błąd podczas rejestracji. Spróbuj ponownie później.", e);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String content = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));
            JSONObject json = XML.toJSONObject(content);
            System.out.println(json.toString(4));

            ObjectMapper objectMapper = new ObjectMapper();
            Pracownik pracownik = objectMapper.readValue(json.toString(), Pracownik.class);
            log.info("Plik załadowany poprawnie");
            return ResponseEntity.status(HttpStatus.OK).body(pracownik);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
