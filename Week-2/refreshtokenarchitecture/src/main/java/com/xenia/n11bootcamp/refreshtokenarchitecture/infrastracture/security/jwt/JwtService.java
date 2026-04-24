package com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.TokenPayload;
import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.port.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Component
@RequestScope
public class JwtService implements TokenService {
    private SecretKey signingKey;
    private long accessExpiration;
    private long refreshExpiration;

    public JwtService(
        @Value("${security.jwt.secret-key}") String secretKey,
        @Value("${security.jwt.access-expiration-time}") long accessExpiration,
        @Value("${security.jwt.refresh-expiration-time}") long refreshExpiration) {

        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        this.signingKey = Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public String issueAccessToken(TokenPayload payload) {
        return generateToken(payload, accessExpiration);
    }

    @Override
    public String issueRefreshToken(TokenPayload payload) {
        return generateToken(payload, refreshExpiration);
    }

    @Override
    public TokenPayload parse(String token) {
        final Claims claims = extractClaims(token);

        return TokenPayload.builder()
            .username(claims.getSubject())
            .build();
    }

    @Override
    public boolean isExpired(String token) {
        final Claims claims = extractClaims(token);
        return claims.getExpiration().before(Date.from(Instant.now()));
    }

    private String generateToken(TokenPayload payload, long expiration) {
        Instant now = Instant.now();
        return Jwts.builder()
            .claims()
                .subject(payload.username())
                .issuer("refresh-token-architecture-api")
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expiration)))
                .and()
            .signWith(signingKey, Jwts.SIG.HS512)
            .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    // Helper methods for when more fields are added to the payload
    @SuppressWarnings("unused")
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    @SuppressWarnings("unused")
    private <T> T extractClaim(String token, String claimName, Class<T> claimType) {
        final Claims claims = extractClaims(token);
        return claims.get(claimName, claimType);
    }
}
