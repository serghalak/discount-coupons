package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;

import java.security.Principal;
import java.util.List;

public interface EventService {

    List<EventResponse> getAllEventsByUserLocation(Principal principal);

    List<EventDetailsResponse> getEventById(Long eventId);

}
