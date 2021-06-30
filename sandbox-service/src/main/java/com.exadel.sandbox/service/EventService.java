package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.FilterRequest;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;

public interface EventService {

    PageList<EventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize);

    EventDetailsResponse getEventById(Long eventId);

    PageList<EventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize);

    PageList<EventResponse> getEventsByFilter(Long userId, FilterRequest filterRequest, Integer pageNumber, Integer pageSize);
}
