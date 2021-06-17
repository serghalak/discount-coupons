package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.EventDetailedDto;
import com.exadel.sandbox.dto.EventShortDto;

import java.security.Principal;
import java.util.List;

public interface EventService {

    List<EventShortDto> getAllEventsByUserLocation(Principal principal);

    List<EventDetailedDto> getAllEventsById(Long eventId);

}
