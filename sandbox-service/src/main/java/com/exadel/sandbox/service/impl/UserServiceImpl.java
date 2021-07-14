package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.notification.SubscriberEnum;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.user.UserRepository;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    @Override
    public Set<User> findAllUsersByVendorSubscription(Long vendorId) {
        return userRepository.findAllUsersByVendorSubscription(vendorId, SubscriberEnum.VENDOR.name());
    }

    @Override
    public Set<User> findAllUsersByCategorySubscription(Long categoryId) {
        return userRepository.findAllUsersByCategorySubscription(categoryId, SubscriberEnum.CATEGORY.name());
    }

    @Override
    public Set<User> findAllUsersByTagsSubscription(Set<Long> ids) {
        return userRepository.findAllUsersByTagsSubscription(ids, SubscriberEnum.TAG.name());
    }
}

