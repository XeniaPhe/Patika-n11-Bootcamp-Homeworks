package com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.security.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xenia.n11bootcamp.refreshtokenarchitecture.application.repository.UserRepository;
import com.xenia.n11bootcamp.refreshtokenarchitecture.domain.entity.User;
import com.xenia.n11bootcamp.refreshtokenarchitecture.infrastracture.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(user.get());
    }
}
