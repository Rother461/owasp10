package com.example.demo.services;

import com.example.demo.exceptions.UserRegistrationException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password) throws UserRegistrationException {
        if (userRepository.userExistsByUsername(username)) {
            log.error("Próba rejestracji użytkownika o istniejącej nazwie: {}", username);
            throw new UserRegistrationException("Użytkownik o tej nazwie już istnieje.");
        }

        try {
            String encodedPassword = passwordEncoder.encode(password);
            User user = new User(username, encodedPassword);
            userRepository.register(username, encodedPassword, user.getClient_id(), user.getClient_secret());
            log.info("Zarejestrowano nowego użytkownika: {}", username);
            return user;
        } catch (Exception e) {
            log.error("Wystąpił błąd podczas rejestracji użytkownika: {}", username, e);
            throw new UserRegistrationException("Nie udało się zarejestrować użytkownika.");
        }
    }
}
