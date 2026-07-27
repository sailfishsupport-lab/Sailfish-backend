package com.sailfish.controller;

import com.sailfish.model.User;
import com.sailfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/sanitized-auth")
public class InputSanitizedController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    // Strict Email Validation Regex to prevent malicious injections
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    @PostMapping("/login")
    public String sanitizedLogin(@RequestBody User loginRequest) {
        // 1. Check for null values
        if (loginRequest.getEmail() == null || loginRequest.getPassword() == null) {
            return "Fields cannot be empty!";
        }

        String sanitizedEmail = loginRequest.getEmail().trim();
        String rawPassword = loginRequest.getPassword();

        // 2. Strict Input Sanitization & Format Check
        if (!EMAIL_PATTERN.matcher(sanitizedEmail).matches()) {
            return "Invalid email format or potential injection detected!";
        }

        // 3. Database lookup using Spring Data JPA (Safe from Injection)
        User existingUser = userRepository.findByEmail(sanitizedEmail);

        // 4. BCrypt Password Match Verification
        if (existingUser != null && passwordEncoder.matches(rawPassword, existingUser.getPassword())) {
            return "Secure Sanitized Login Successful!";
        } else {
            return "Invalid Email or Password!";
        }
    }
}
