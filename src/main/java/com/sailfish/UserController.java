package com.sailfish.controller;

import com.sailfish.model.User;
import com.sailfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Data ko database me save karna
        userRepository.save(user);
        return ResponseEntity.ok("Sailfish par account successfully create aur data server me feed ho gaya hai!");
    }
}
