package com.sailfish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/send-email")
    public String sendEmailOtp(@RequestParam String email) {
        // 6 digit ka random OTP generate karna
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Sailfish Verification OTP");
            message.setText("Aapka Sailfish verification OTP code hai: " + otp);
            
            mailSender.send(message);
            return "OTP sent successfully to email!";
        } catch (Exception e) {
            return "Failed to send email OTP: " + e.getMessage();
        }
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String userOtp, @RequestParam String savedOtp) {
        if (userOtp.equals(savedOtp)) {
            return "OTP Verified Successfully!";
        }
        return "Invalid OTP! Please try again.";
    }
}
