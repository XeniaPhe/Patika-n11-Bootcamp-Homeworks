package com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.port;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto.TokenPayload;

public interface TokenService {
    String issueAccessToken(TokenPayload payload);
    String issueRefreshToken(TokenPayload payload);
    TokenPayload parse(String token);
    boolean isExpired(String token);
}
