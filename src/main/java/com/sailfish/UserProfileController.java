package com.sailfish.controller;

import com.sailfish.model.User;
import com.sailfish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-user")
    public User getUserProfile(@RequestParam String email) {
        User existingUser = userRepository.findByEmail(email);
        
        if (existingUser != null) {
            // Password ko hide karke baaki details bhej sakte hain security ke liye
            existingUser.setPassword(null);
            return existingUser;
        } else {
            return null;
        }
    }
}
