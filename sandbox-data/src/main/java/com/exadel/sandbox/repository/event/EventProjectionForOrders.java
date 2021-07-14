package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.vendorinfo.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventProjectionForOrders {

    LocalDateTime getDateEvent();

    Event getE();
}
