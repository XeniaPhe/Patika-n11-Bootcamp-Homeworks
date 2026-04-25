package com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long id;

    private String username;
    private String password;

    private String fullName;
}
