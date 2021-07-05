package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.FilterRequest;
import com.exadel.sandbox.dto.request.event.EventRequest;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Status;
import com.exadel.sandbox.model.vendorinfo.Tag;
import com.exadel.sandbox.repository.UserRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.repository.location_repository.LocationRepository;
import com.exadel.sandbox.repository.tag.TagRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    private final LocationRepository locationRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;

    @Override
    public PageList<EventDetailsResponse> getAll(Integer pageNumber, Integer pageSize) {
        final Page<Event> eventsPage = eventRepository.findAll(PageRequest.of(getPageNumber(pageNumber),
                getPageSize(pageSize), Sort.by("id").descending()));

        return new PageList<>(eventMapper.eventListToDetailEventResponse(eventsPage.getContent()), eventsPage);
    }

    @Override
    public PageList<CustomEventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize) {
        var city = cityRepository.findCityByUserId(userId);
        return getEventResponsesByCity(city.getId(), getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<CustomEventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize) {
        return getEventResponsesByCity(cityId, getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<CustomEventResponse> getAllEventsByDescription(Long cityId, String search,
                                                                   Integer pageNumber, Integer pageSize) {

        Page<Event> eventsPage = eventRepository.findEventByDescription(("%" + search + "%"),
                cityId, PageRequest.of(getPageNumber(pageNumber), getPageSize(pageSize),
                        Sort.by(Sort.Direction.DESC, "dateEnd")));
        return new PageList<>(eventMapper.
                eventListToCustomEventResponseListByCityId(eventsPage.getContent(), cityId), eventsPage);
    }

    private PageList<CustomEventResponse> getEventResponsesByCity(Long cityId, Integer pageNumber, Integer pageSize) {
        Page<Event> eventsPage = eventRepository.findEventByCityId(cityId,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "dateEnd")));

        return new PageList<>(eventMapper.
                eventListToCustomEventResponseListByCityId(eventsPage.getContent(), cityId), eventsPage);
    }

    @Override
    public EventDetailsResponse getEventById(Long eventId) {

        return Optional.ofNullable(eventRepository.findEventById(eventId))
                .map(eventMapper::eventToEventDetailResponse)
                .orElseThrow(() -> new EntityNotFoundException("entity with id " + eventId + " not found"));
    }

    @Override
    public PageList<CustomEventResponse> getEventsByFilter(Long userId, FilterRequest filterRequest,
                                                           Integer pageNumber, Integer pageSize) {
        var sort = getSorting(filterRequest.getStatus());
        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);

        if (filterRequest.getLocationId() == 0 &&
                filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {
            var city = cityRepository.findCityByUserId(userId);

            return getEventResponsesByCity(city.getId(), pageNumber, pageSize);
        }

        if (filterRequest.getLocationId() == 0) {
            var city = cityRepository.findCityByUserId(userId);
            filterRequest.setLocationId(city.getId());
            filterRequest.setCity(true);
        }

        if (!filterRequest.getTagsIdSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {

            return getCustomEventResponsePageListByTags(filterRequest, pageNumber, pageSize, sort);

        }

        if (!filterRequest.getTagsIdSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            return getCustomEventResponsePageListByTagsAndVedors(filterRequest, pageNumber, pageSize, sort);
        }

        if (!filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {

            return getCustomEventResponsePageListByCategories(filterRequest, pageNumber, pageSize, sort);
        }

        if (!filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            return getCustomEventResponsePageListByCategoriesAndVendors(filterRequest, pageNumber, pageSize, sort);
        }

        if (filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            return getCustomEventResponsePageListByVendors(filterRequest, pageNumber, pageSize, sort);
        }
        return null;
    }

    private PageList<CustomEventResponse> getCustomEventResponsePageListByVendors
            (FilterRequest filterRequest, Integer pageNumber, Integer pageSize, Sort sort) {
        final Page<Event> eventPage;

        if (filterRequest.isCity()) {
            eventPage = eventRepository.findByVendorsByCity(filterRequest.getLocationId(),
                    filterRequest.getVendorsIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);

        } else {
            eventPage = eventRepository.findByByVendorsCountry(filterRequest.getLocationId(),
                    filterRequest.getVendorsIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);
        }
    }

    private PageList<CustomEventResponse> getCustomEventResponsePageListByCategoriesAndVendors
            (FilterRequest filterRequest, Integer pageNumber, Integer pageSize, Sort sort) {
        final Page<Event> eventPage;

        if (filterRequest.isCity()) {
            eventPage = eventRepository.findByCategoryByVendorsByCity(filterRequest.getLocationId(),
                    filterRequest.getVendorsIdSet(),
                    filterRequest.getCategoriesIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);

        } else {
            eventPage = eventRepository.findByCategoryByByVendorsCountry(filterRequest.getLocationId(),
                    filterRequest.getVendorsIdSet(),
                    filterRequest.getCategoriesIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);
        }
    }

    private PageList<CustomEventResponse> getCustomEventResponsePageListByCategories
            (FilterRequest filterRequest, Integer pageNumber, Integer pageSize, Sort sort) {
        final Page<Event> eventPage;

        if (filterRequest.isCity()) {
            eventPage = eventRepository.findByCategoryByCity(filterRequest.getLocationId(),
                    filterRequest.getCategoriesIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);

        } else {
            eventPage = eventRepository.findByCategoryByCountry(filterRequest.getLocationId(),
                    filterRequest.getCategoriesIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);
        }
    }

    private PageList<CustomEventResponse> getCustomEventResponsePageListByTagsAndVedors
            (FilterRequest filterRequest, Integer pageNumber, Integer pageSize, Sort sort) {
        final Page<Event> eventPage;
        if (filterRequest.isCity()) {
            eventPage = eventRepository.findByTagsByVendorsByCity(filterRequest.getLocationId(),
                    filterRequest.getVendorsIdSet(),
                    filterRequest.getTagsIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);

        } else {
            eventPage = eventRepository.findByTagsByVendorsByCountry(filterRequest.getLocationId(),
                    filterRequest.getVendorsIdSet(),
                    filterRequest.getTagsIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);
        }
    }

    private PageList<CustomEventResponse> getCustomEventResponsePageListByTags(FilterRequest filterRequest,
                                                                               Integer pageNumber, Integer pageSize, Sort sort) {
        final Page<Event> eventPage;

        if (filterRequest.isCity()) {
            eventPage = eventRepository.findByTagsByCity(filterRequest.getLocationId(),
                    filterRequest.getTagsIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);

        } else {
            eventPage = eventRepository.findByTagsByCountry(filterRequest.getLocationId(),
                    filterRequest.getTagsIdSet(),
                    filterRequest.getStatus(),
                    PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(eventPage.getContent(),
                    filterRequest.getLocationId()), eventPage);
        }
    }

    private Sort getSorting(Status status) {

        switch (status) {
            case NEW:
                return Sort.by(Sort.Direction.DESC, "dateOfCreation");
            case COMING_SOON:
                return Sort.by(Sort.Direction.ASC, "dateBegin");
            case ACTIVE:
                return Sort.by(Sort.Direction.DESC, "dateEnd");
            case EXPIRED:
                return Sort.by(Sort.Direction.DESC, "name");
            default:
                return Sort.by(Sort.Direction.DESC, "name");
        }
    }

    @Override
    public boolean deleteEventById(Long eventId) {
        final var event = eventRepository.getById(eventId);

        return event.getUserFeedbacks() == null &&
                event.getUserOrders() == null &&
                event.getUserSavedEvents() == null;
    }

    @Override
    public PageList<CustomEventResponse> saveEvent(Long vendorId, EventRequest eventRequest) {

        if (vendorId <= 0 || vendorId == null || eventRequest == null) {
            throw new IllegalArgumentException("Enter wright information");
        }

        final Set<Location> locationsById = locationRepository.getLocationsById(eventRequest.getLocationsId());
        final Set<Tag> tagsById = tagRepository.getTagsById(eventRequest.getTagsId());

        final Event event = eventMapper.eventRequestToEvent(eventRequest, locationsById, tagsById);


        return null;
    }


    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }


}
