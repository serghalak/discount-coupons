package com.exadel.sandbox.service;

import com.exadel.sandbox.model.user.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User findUserByEmail(String email);
}
