package com.exadel.sandbox.security;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@AllArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("User name: " + email + " not found");
        });

        return new org.springframework.security.core.userdetails.User(
                user.getEmail()
                , user.getPassword()
                , user.getEnabled()
                , user.getAccountNonExpired()
                , user.getCredentialsNonExpired()
                , user.getAccountNonLocked()
                , convertToSpringAuthorities(user.getRole().name()));
    }

    private Collection<? extends GrantedAuthority> convertToSpringAuthorities(String role) {
        if (role != null) {
            return Collections.singletonList(new SimpleGrantedAuthority(role));
        } else {
            return new HashSet<>();
        }
    }
}
