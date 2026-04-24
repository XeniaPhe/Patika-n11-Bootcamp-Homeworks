package com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.mapper.impl;

import org.springframework.stereotype.Component;

import com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity.User;
import com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.entity.UserEntity;
import com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.persistence.mapper.BaseEntityMapper;

@Component
public class UserMapper extends BaseEntityMapper<User, UserEntity> {
    @Override
    public User toDomain(UserEntity entity) {
        return new User(
            entity.getId(),
            entity.getUsername(),
            entity.getPassword(),
            entity.getFullName()
        );
    }

    @Override
    public UserEntity fromDomain(User user) {
        return new UserEntity(
            user.getId(),
            user.getUsername(),
            user.getPassword(),
            user.getFullName()
        );
    }
}
