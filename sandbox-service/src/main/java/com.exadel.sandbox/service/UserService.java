package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.request.user.UserRequest;
import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.user.UserResponse;
import com.exadel.sandbox.model.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> findAll();

    UserResponse findByName(final String name);

    EventShortResponse saveEventToOrder(final long eventId, final long userId);

    EventShortResponse saveEventToSaved(final long userId, final long eventId);

}
