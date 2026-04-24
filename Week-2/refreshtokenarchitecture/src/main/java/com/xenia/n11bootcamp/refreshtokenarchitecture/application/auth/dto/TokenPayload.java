package com.xenia.n11bootcamp.refreshtokenarchitecture.application.auth.dto;

import com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity.User;

import lombok.Builder;

@Builder
public record TokenPayload(String username) {
    public static TokenPayload fromUser(User user) {
        return new TokenPayload(user.getUsername());
    }
}
