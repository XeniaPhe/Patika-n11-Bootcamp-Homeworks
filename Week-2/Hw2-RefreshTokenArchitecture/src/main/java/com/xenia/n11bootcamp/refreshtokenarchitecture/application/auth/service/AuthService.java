package com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.TokenPayload;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.request.LoginRequest;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.request.RefreshRequest;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.request.RegisterRequest;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.response.AuthResponse;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.response.RefreshResponse;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.exception.UsernameAlreadyExistsException;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.port.TokenService;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.repository.UserRepository;
import com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepo;

    public AuthResponse register(RegisterRequest request) {
        if (userRepo.findByUsername(request.username()).isPresent()) {
            throw new UsernameAlreadyExistsException();
        }

        User user = User.builder()
            .username(request.username())
            .password(passwordEncoder.encode(request.password()))
            .fullName(request.fullName())
            .build();

        userRepo.save(user);
        TokenPayload payload = TokenPayload.fromUser(user);
        return new AuthResponse(
            tokenService.issueAccessToken(payload),
            tokenService.issueRefreshToken(payload)
        );
    }

    public AuthResponse login(LoginRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );

        TokenPayload payload = TokenPayload.builder().username(request.username()).build();
        return new AuthResponse(
            tokenService.issueAccessToken(payload),
            tokenService.issueRefreshToken(payload)
        );
    }

    public RefreshResponse refresh(RefreshRequest request) {
        if (tokenService.isExpired(request.refreshToken())) {
            throw new RuntimeException("Refresh token expired, log-in required.");
        }

        TokenPayload payload = tokenService.parse(request.refreshToken());
        return new RefreshResponse(tokenService.issueAccessToken(payload));
    }
}