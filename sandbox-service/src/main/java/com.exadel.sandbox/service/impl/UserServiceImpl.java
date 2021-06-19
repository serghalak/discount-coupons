package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.request.user.UserRequest;
import com.exadel.sandbox.dto.response.location.UserLocationResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.location_repository.CountryRepository;
import com.exadel.sandbox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;

    private final UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserResponse findByName(String name) {
        User user = userRepository.findByEmail(name);
        return userMapper.userToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        return null;
    }

    private UserLocationResponse getLocation(User user) {
        Location userLocation = user.getLocation();
        return UserLocationResponse.builder()
                .country(userLocation.getCity().getCountry().getName())
                .city(userLocation.getCity().getName())
                .street(userLocation.getStreet())
                .number(userLocation.getNumber())
                .latitude(userLocation.getLatitude())
                .longitude(userLocation.getLongitude())
                .build();
    }
}
