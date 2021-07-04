package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse findByName(String name) {
        var user = userRepository.findByEmail(name);
        return userMapper.userToUserResponse(user);
    }

}

