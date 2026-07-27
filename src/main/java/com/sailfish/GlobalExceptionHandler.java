package com.sailfish.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // Server crash hone se bachane ke liye error ko catch karke message bhej rahe hain
        return new ResponseEntity<>("Something went wrong on the server: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
