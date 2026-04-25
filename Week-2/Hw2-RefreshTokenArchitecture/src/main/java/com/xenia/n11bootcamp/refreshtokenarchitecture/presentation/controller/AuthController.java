package com.xenia.n11bootcamp.refreshtokenarchitecture.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.request.LoginRequest;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.request.RefreshRequest;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.request.RegisterRequest;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.response.AuthResponse;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.response.RefreshResponse;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register new user")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest request) {
        RefreshResponse response = authService.refresh(request);
        return ResponseEntity.ok(response);
    }
}
