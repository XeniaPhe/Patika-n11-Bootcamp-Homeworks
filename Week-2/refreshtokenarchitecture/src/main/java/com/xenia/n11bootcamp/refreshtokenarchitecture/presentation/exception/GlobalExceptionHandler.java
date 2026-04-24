package com.xenia.n11bootcamp.refreshtokenarchitecture.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.exception.TokenExpiredException;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.exception.UsernameAlreadyExistsException;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameNotFound(UsernameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
            .body("Registration failed.");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(UsernameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Authentication failed.");
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> handleTokenExpiredException(TokenExpiredException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Authentication failed: " + e.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException(JwtException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body("Authentication failed.");
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleEverythingElse(Exception e) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
            .body("Something went wrong.");
    }
}
