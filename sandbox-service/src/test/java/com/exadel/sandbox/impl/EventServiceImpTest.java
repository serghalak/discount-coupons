package com.exadel.sandbox.impl;

import com.exadel.sandbox.service.impl.EventServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventServiceImpTest {

    @Autowired
    private EventServiceImp eventService;

    @Test
    void getAllEventsByUserLocation() {
    }

    @Test
    void getEventById() {
    }
}