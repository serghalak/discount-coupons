package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.repository.event.EventRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private EventRepository eventRepository;

    @MockBean
    private ModelMapper mapper;

    @Test
    void findAll() {
    }

    @Test
    void findByName() {
    }

    @Test
    void saveEventToOrder() {
    }

    @Test
    void saveEventToSaved() {
    }

    @Test
    void removeEventFromOrder() {
    }

    @Test
    void removeEventFromSaved() {
    }

    @Test
    void getAllFromOrder() {
    }

    @Test
    void getAllFromSaved() {
    }
}