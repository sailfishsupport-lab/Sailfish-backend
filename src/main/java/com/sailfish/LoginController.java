package com.sailfish.controller;

import com.sailfish.model.User;
import com.sailfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginRequest) {
        User existingUser = userRepository.findByEmail(loginRequest.getEmail());
        
        if (existingUser != null && existingUser.getPassword().equals(loginRequest.getPassword())) {
            return "Login Successful!";
        } else {
            return "Invalid Email or Password!";
        }
    }
}
