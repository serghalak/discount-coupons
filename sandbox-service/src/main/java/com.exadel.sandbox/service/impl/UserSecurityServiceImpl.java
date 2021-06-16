package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.DetailsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserSecurityServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email);
        return DetailsUser.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .email(user.getEmail())
                .isEnabled(true)
                .role(Set.of(user.getRole()))
                .build();
    }

}
