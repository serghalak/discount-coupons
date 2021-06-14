package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.request.UserDto;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.DetailsUser;
import com.exadel.sandbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserSecurityServiceImpl implements UserDetailsService, UserService {

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

    @Override
    public UserDto findByName(String name) {
        var user = userRepository.findByEmail(name);
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .location(user.getLocation())
                .role(user.getRole())
                .build();
    }

    @Override
    public DetailsUser findUser(String email) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

}
