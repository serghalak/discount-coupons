package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.mail.MailUtil;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.mappers.user.UserMapper;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EventRepository eventRepository;
    private final EventShortMapper eventShortMapper;
    private final ModelMapper mapper;
    private final MailUtil mailUtil;

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
    public EventShortResponse saveEventToOrder(Long userId, Long eventId, String email) {
        var event = verifyEventId(eventId);
        mailUtil.sendSimpleMessage(email);
        userRepository.insertIntoUserOrder(eventId, userId);
        return eventShortMapper.eventToEventShortResponse(verifyEventId(eventId));
    }

    @Override
    public String removeEventFromOrder(Long eventId, Long userId) {
        var exist = userRepository.getOneEventsFromUserOrder(eventId, userId);
        Optional.ofNullable(exist)
                .orElseThrow(() -> new EntityNotFoundException("Event does not exist in Order"));
        userRepository.deleteFromUserOrder(eventId, userId);
        return "Event successfully removed from User Order ";
    }

    @Override
    public List<EventResponse> getAllFromOrder(Long userId) {
        if (userRepository.getAllEventsFromUserOrder(userId).isEmpty()) {
            throw new EntityNotFoundException("Your order list is empty");
        }
        return userRepository.getAllEventsFromUserOrder(userId).stream()
                .map(event -> mapper.map(event, EventResponse.class))
                .collect(Collectors.toList());
    }

    private Event verifyEventId(Long eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Id is not correct");
        }

        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " does not found"));

    }

}

