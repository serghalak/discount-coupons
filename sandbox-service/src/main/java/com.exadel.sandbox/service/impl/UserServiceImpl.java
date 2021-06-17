package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.UserDto;
import com.exadel.sandbox.dto.UserLocationDto;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.location_repository.CountryRepository;
import com.exadel.sandbox.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto findByName(String name) {
        User user = userRepository.findByEmail(name);
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .location(getLocation(user))
                .build();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return null;
    }

    private UserLocationDto getLocation(User user) {
        Location userLocation = user.getLocation();
        return UserLocationDto.builder()
                .country(userLocation.getCity().getCountry().getName())
                .city(userLocation.getCity().getName())
                .street(userLocation.getStreet())
                .number(userLocation.getNumber())
                .latitude(userLocation.getLatitude())
                .longitude(userLocation.getLongitude())
                .build();
    }
}
