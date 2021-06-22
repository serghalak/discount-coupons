package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.location_repository.CountryRepository;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public EventShortResponse saveEventToOrder(Long eventId, Long userId) {
        var event = eventRepository.findById(Math.toIntExact(eventId))
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " does not found"));
        if (eventId <= 0) {
            throw new IllegalArgumentException("Id is not correct");
        }
        userRepository.insertIntoUserOrder(eventId, userId);
        return eventShortMapper.eventToEventShortResponse(event);
    }

    @Override
    public EventShortResponse saveEventToSaved(Long userId, Long eventId) {
        var event = eventRepository.findById(Math.toIntExact(eventId))
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " does not found"));
        if (eventId <= 0) {
            throw new IllegalArgumentException("Id is not correct");
        }
        userRepository.insertIntoUserSaved(eventId, userId);
        return eventShortMapper.eventToEventShortResponse(event);
    }

}
