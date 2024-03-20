package com.example.demo.controllers;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.LoginReq;
import com.example.demo.entities.ErrorRes;
import com.example.demo.entities.LoginRes;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReq loginReq) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));

            User user = userRepository.login(loginReq.getUsername(), loginReq.getPassword());
            String token = jwtUtil.createToken(user);
            LoginRes loginRes = new LoginRes(user.getUsername(), token);

            return ResponseEntity.ok(loginRes);

        } catch (BadCredentialsException e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}