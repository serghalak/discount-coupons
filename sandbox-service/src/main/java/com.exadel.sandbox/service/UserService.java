package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.UserDto;
import com.exadel.sandbox.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    UserDto findByName(final String name);

    UserDto updateUser(final UserDto userDto);

}
