package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.user.UserRepository;
import com.exadel.sandbox.repository.user.UserSavedRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FavouriteServiceImplTest {

    @InjectMocks
    private FavouriteServiceImpl favouriteService;
    @Mock
    private UserSavedRepository userSavedRepository;
    @Mock
    private VendorRepository vendorRepository;
    @Mock
    private ModelMapper mapper;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private EventMapper eventMapper;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private UserRepository userRepository;


    @Test
    void eventsLocationsFromSaved() {
    }

    @Test
    void vendorsFromSaved() {
    }

    @Test
    void categoriesFromSaved() {
    }

    @Test
    void getAllFromSaved() {
    }

    @Test
    void saveEventToSaved() {
    }

    @Test
    void removeEventFromSaved() {
    }
}