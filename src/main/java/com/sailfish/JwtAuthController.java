package com.sailfish.controller;

import com.sailfish.model.User;
import com.sailfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/jwt-auth")
public class JwtAuthController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/login-token")
    public String generateTokenLogin(@RequestBody User loginRequest) {
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return "Email and Password are required!";
        }

        User existingUser = userRepository.findByEmail(loginRequest.getEmail().trim());

        if (existingUser != null && passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
            // Generate a secure random token session
            String token = UUID.randomUUID().toString();
            return "Login Successful! Token: " + token;
        } else {
            return "Invalid Email or Password!";
        }
    }
}
