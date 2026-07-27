package com.sailfish.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/protected")
public class JwtInterceptorController {

    @GetMapping("/dashboard")
    public String accessDashboard(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        // Check if token exists and starts with Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            // Here token validation logic goes
            if (!token.isEmpty()) {
                return "Access Granted! Welcome to your secure dashboard.";
            }
        }
        
        return "Access Denied! Invalid or missing token.";
    }
}
