package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.event.EventResponseFoOrders;

public interface OrderService {

    String saveEventToOrder(final Long userId, final Long eventId, final String email);

    String removeEventFromOrder(final Long userId, final Long eventId);

    PageList<EventResponseFoOrders> getAllFromOrder(final Long userId, Integer pageNumber, Integer pageSize);
}
