package com.sailfish.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LogoutController {

    @PostMapping("/logout")
    public String logoutUser(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            // Yahan token ko blacklist ya clear karne ka logic aayega
            // Abhi ke liye client side par token delete karne ka signal de rahe hain
            return "Logout Successful! Please clear your token from client storage.";
        }
        
        return "No active session found or invalid token!";
    }
}
