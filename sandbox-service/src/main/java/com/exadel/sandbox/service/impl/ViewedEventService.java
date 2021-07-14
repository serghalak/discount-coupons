package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.user.UserRepository;
import com.exadel.sandbox.repository.user.UserViewedEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ViewedEventService {

    private final UserViewedEventRepository userViewedEventRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public void saveEventToViewed(Long userId, Long eventId) {
        if (userRepository.existsById(userId) && eventRepository.existsById(eventId)) {
            if (userViewedEventRepository.existsIntoViewed(eventId, userId) == 0) {
                userViewedEventRepository.insertIntoViewed(eventId, userId, LocalDateTime.now());
            }
        }
    }

}
