package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.dto.response.event.EventShortResponse;
import com.exadel.sandbox.dto.response.location.LocationShortResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.mappers.category.CategoryShortMapper;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.mappers.event.EventShortMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.user.UserSavedRepository;
import com.exadel.sandbox.service.FavouriteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final UserSavedRepository userSavedRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ModelMapper mapper;
    private final EventShortMapper eventShortMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryShortMapper categoryMapper;
    private final EventMapper eventMapper;

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
        List<CategoryShortResponse> categoryShortResponses =
                categoryRepository.getAllCategoriesFromSaved(2L).stream()
                .map(el->mapper.map(el, CategoryShortResponse.class))
                .collect(Collectors.toList());
        System.out.println(categoryShortResponses.toString());
        return categoryShortResponses;
    }

    @Override
    public PageList<CustomEventResponse> getAllFromSaved(Long userId,Long cityId,  Integer pageNumber, Integer pageSize) {
        final Page<Event> allEventFromSaved = userSavedRepository.getAllEventsFromUserSaved(userId,
                PageRequest.of(getPageNumber(pageNumber), getPageSize(pageSize), Sort.by(Sort.Direction.DESC, "dateEnd")) );
        if (allEventFromSaved.isEmpty()) {
            throw new EntityNotFoundException("Your saved list is empty");
        }
        return new PageList<CustomEventResponse>(
                eventMapper.eventListToCustomEventResponseListByCityId(allEventFromSaved.getContent(),
                        cityId),
                allEventFromSaved);
    }

    @Override
    public EventShortResponse saveEventToSaved(Long userId, Long eventId) {
        var event = verifyEventId(eventId);
        userRepository.insertIntoUserSaved(eventId, userId);
        return eventShortMapper.eventToEventShortResponse(event);
    }

    @Override
    public String removeEventFromSaved(Long userId, Long eventId) {
        var exist = userSavedRepository.getOneEventsFromUserSaved(eventId, userId);
        Optional.ofNullable(exist)
                .orElseThrow(() -> new EntityNotFoundException("Event does not exist in Saved"));
        userSavedRepository.deleteFromUserSaved(eventId, userId);
        return "Event successfully removed from User Saved ";
    }

    private Event verifyEventId(Long eventId) {
        if (eventId <= 0) {
            throw new IllegalArgumentException("Id is not correct");
        }

        return eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " does not found"));

    }
    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 0 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
