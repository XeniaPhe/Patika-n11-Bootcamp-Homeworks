package com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.repository.UserRepository;
import com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity.User;
import com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.jpa.JpaUserRepository;
import com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.mapper.impl.UserMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaRepo;
    private final UserMapper mapper;

    @Override
    public User save(User entity) {
        return mapper.toDomain(jpaRepo.save(mapper.fromDomain(entity)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return mapper.toDomain(jpaRepo.findById(id));
    }

    @Override
    public List<User> findAll() {
        return mapper.toDomainEntities(jpaRepo.findAll());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return mapper.toDomain(jpaRepo.findByUsername(username));
    }
}
