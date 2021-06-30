package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.mappers.category.CategoryShortMapper;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.repository.user.UserSavedRepository;
import com.exadel.sandbox.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private final UserSavedRepository userSavedRepository;
    private final ModelMapper mapper;
    private final EventShortMapper eventMapper;
    private final CategoryShortMapper categoryMapper;

    private final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<LocationShortResponse> eventsLocationsFromSaved(Long userId) {
        return null;
    }

    @Override
    public List<VendorShortResponse> vendorsFromSaved(Long userId) {
        return null;
    }

    @Override
    public List<CategoryShortResponse> categoriesFromSaved(Long userId) {
        return null;
    }

    @Override
    public List<EventResponse> getAllFromSaved(Long userId) {
        if (userSavedRepository.getAllEventsFromUserSaved(userId).isEmpty()) {
            throw new EntityNotFoundException("Your saved list is empty");
        }
        return userSavedRepository.getAllEventsFromUserSaved(userId).stream()
                .map(event -> mapper.map(event, EventResponse.class))
                .sorted(Comparator.comparing(EventResponse::getDateEnd))
                .collect(Collectors.toList());
    }
}
