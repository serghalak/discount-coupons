package com.exadel.sandbox.service.impl;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
