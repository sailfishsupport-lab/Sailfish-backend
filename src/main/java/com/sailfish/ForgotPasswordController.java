package com.sailfish.controller;

import com.sailfish.model.User;
import com.sailfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody User request) {
        User existingUser = userRepository.findByEmail(request.getEmail());
        
        if (existingUser ==null) {
             return "email not found";
        } else {
        existinguser .setPassword(request.getPassword());
        userRepository .save(existingUser);
        return "Password Updated
        Succesfully	!";
        }
    }
}
