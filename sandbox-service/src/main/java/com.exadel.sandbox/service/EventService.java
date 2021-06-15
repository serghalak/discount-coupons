package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.EventDto;
import java.security.Principal;
import java.util.List;


public interface EventService {

    List<EventDto> getAllEventsByUserLocation(Principal principal);
}
