package com.xenia.n11bootcamp.refreshtokenarchitecture.application.repository;

import java.util.Optional;

import com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity.User;

public interface UserRepository extends GenericRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
