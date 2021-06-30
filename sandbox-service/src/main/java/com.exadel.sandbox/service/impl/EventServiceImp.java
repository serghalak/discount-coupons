package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.FilterRequest;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Status;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    private final EventMapper eventMapper;

    @Override
    public PageList<EventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize) {
        var city = cityRepository.findCityByUserId(userId);
        return getEventResponses(city.getId(), getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<EventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize) {
        return getEventResponses(cityId, getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<EventResponse> getAllEventsByDescription(Long cityId, String search,
                                                             Integer pageNumber, Integer pageSize) {

        Page<Event> eventsPage = eventRepository.findEventByDescription(("%" + search + "%"),
                cityId, PageRequest.of(pageNumber, pageSize));
        return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
    }

    private PageList<EventResponse> getEventResponses(Long cityId, Integer pageNumber, Integer pageSize) {
        Page<Event> eventsPage = eventRepository.findEventByCityId(cityId,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "dateEnd")));

        return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);

    }

    @Override
    public EventDetailsResponse getEventById(Long eventId) {

        return Optional.ofNullable(eventRepository.findEventById(eventId))
                .map(eventMapper::eventToEventDetailResponse)
                .orElseThrow(() -> new EntityNotFoundException("entetity with id " + eventId + " not found"));
    }

    @Override
    public PageList<EventResponse> getEventsByFilter(Long userId, FilterRequest filterRequest, Integer pageNumber, Integer pageSize) {
        Sort sort = getSorting(filterRequest.getStatus());
        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);
        if (filterRequest.getLocationId() == 0 &&
                filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIsSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {
            var city = cityRepository.findCityByUserId(userId);

            return getEventResponses(city.getId(), pageNumber, pageSize);
        }
        if (filterRequest.getLocationId() == 0) {
            var city = cityRepository.findCityByUserId(userId);
            filterRequest.setLocationId(city.getId());
            filterRequest.setCity(true);
        }
        if (!filterRequest.getTagsIsSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {

            Page<Event> eventsPage = filterRequest.isCity() ?
                    eventRepository.findByTagsByCity(filterRequest.getLocationId(),
                            filterRequest.getTagsIsSet(),
                            filterRequest.getStatus(),
                            PageRequest.of(pageNumber, pageSize, sort)) :
                    eventRepository.findByTagsByCountry(filterRequest.getLocationId(),
                            filterRequest.getTagsIsSet(),
                            filterRequest.getStatus(),
                            PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
        }

        if (!filterRequest.getTagsIsSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            Page<Event> eventsPage = filterRequest.isCity() ?
                    eventRepository.findByTagsByVendorsByCity(filterRequest.getLocationId(),
                            filterRequest.getVendorsIdSet(),
                            filterRequest.getTagsIsSet(),
                            PageRequest.of(pageNumber, pageSize, sort)) :
                    eventRepository.findByTagsByVendorsByCountry(filterRequest.getLocationId(),
                            filterRequest.getVendorsIdSet(),
                            filterRequest.getTagsIsSet(),
                            PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
        }

        if (!filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIsSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {

            Page<Event> eventsPage = filterRequest.isCity() ?
                    eventRepository.findByCategoryByCity(filterRequest.getLocationId(),
                            filterRequest.getCategoriesIdSet(),
                            PageRequest.of(pageNumber, pageSize, sort)) :
                    eventRepository.findByCategoryByCountry(filterRequest.getLocationId(),
                            filterRequest.getCategoriesIdSet(),
                            PageRequest.of(pageNumber, pageSize, sort));

            return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
        }

        if (!filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIsSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            Page<Event> eventsPage = filterRequest.isCity() ?
                    eventRepository.findByCategoryByVendorsByCity(filterRequest.getLocationId(),
                            filterRequest.getVendorsIdSet(),
                            filterRequest.getCategoriesIdSet(),
                            PageRequest.of(pageNumber, pageSize)) :
                    eventRepository.findByCategoryByByVendorsCountry(filterRequest.getLocationId(),
                            filterRequest.getVendorsIdSet(),
                            filterRequest.getCategoriesIdSet(),
                            PageRequest.of(pageNumber, pageSize));

            return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
        }

        return null;
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

    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;

    }
}
