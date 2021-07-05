package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;

public interface OrderService {

    String saveEventToOrder(final Long userId, final Long eventId, final String email);

    String removeEventFromOrder(final Long userId, final Long eventId);

    PageList<CustomEventResponse> getAllFromOrder(final Long userId, Long cityId, Integer pageNumber, Integer pageSize);
}
