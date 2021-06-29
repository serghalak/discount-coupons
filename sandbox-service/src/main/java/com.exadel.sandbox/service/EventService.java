package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;

public interface EventService {

    //    List<EventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize);
    PageList<EventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize);

    EventDetailsResponse getEventById(Long eventId);

    //    List<EventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize);
    PageList<EventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize);
}
