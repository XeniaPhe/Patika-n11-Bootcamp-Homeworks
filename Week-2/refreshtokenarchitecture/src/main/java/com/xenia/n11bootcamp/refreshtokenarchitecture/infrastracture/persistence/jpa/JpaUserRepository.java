package com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.jpa;

import com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.entity.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
