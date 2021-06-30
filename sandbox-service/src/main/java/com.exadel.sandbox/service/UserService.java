package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    UserResponse findByName(final String name);

    EventShortResponse saveEventToOrder(final Long userId, final Long eventId, final String email);

    EventShortResponse saveEventToSaved(final Long userId, final Long eventId);

    String removeEventFromOrder(final Long userId, final Long eventId);

    String removeEventFromSaved(final Long userId, final Long eventId);

    List<EventResponse> getAllFromOrder(final Long userId);

}
