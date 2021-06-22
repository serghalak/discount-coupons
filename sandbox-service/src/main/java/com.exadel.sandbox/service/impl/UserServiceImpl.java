package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.location_repository.CountryRepository;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final UserMapper userMapper;
    private final EventRepository eventRepository;
    private final EventShortMapper eventShortMapper;

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
    public EventShortResponse saveEventToOrder(long eventId, long userId ) {
        userRepository.insertUserOrder(eventId,userId);
        Event event = eventRepository.findEventById(eventId);
        System.out.println(eventShortMapper.eventToEventShortResponse(event).toString());
        return eventShortMapper.eventToEventShortResponse(eventRepository.findEventById(eventId));
    }

    @Override
    public EventShortResponse saveEventToSaved(long userId, long eventId) {
        return null;
    }

}
