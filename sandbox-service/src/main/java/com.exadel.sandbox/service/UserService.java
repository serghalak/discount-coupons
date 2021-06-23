package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    UserResponse findByName(final String name);

    EventShortResponse saveEventToOrder(final Long eventId, final Long userId);

    EventShortResponse saveEventToSaved(final Long userId, final Long eventId);

    void removeEventFromOrder(final Long userId, final Long eventId);

    void removeEventFromSaved(final Long userId, final Long eventId);

    List<EventResponse> getAllFromOrder(final Long userId);

    List<EventResponse> getAllFromSaved(final Long userId);

}
