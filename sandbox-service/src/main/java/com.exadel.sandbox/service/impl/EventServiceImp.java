package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.FilterRequest;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.dto.response.event.EventResponse;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Status;
import com.exadel.sandbox.repository.category.CategoryRepository;
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
import java.util.Set;

@AllArgsConstructor
@Service
public class EventServiceImp implements EventService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final EventRepository eventRepository;
    private final CityRepository cityRepository;
    private final CategoryRepository categoryRepository;
    private final EventMapper eventMapper;

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
    public PageList<EventResponse> getAllEventsByDescription(Long cityId, String search,
                                                             Integer pageNumber, Integer pageSize) {

        Page<Event> eventsPage = eventRepository.findEventByDescription(("%" + search + "%"),
                cityId, PageRequest.of(pageNumber, pageSize));
        return new PageList<>(eventMapper.eventListToEventResponseList(eventsPage.getContent()), eventsPage);
    }

    private PageList<CustomEventResponse> getEventResponsesByCity(Long cityId, Integer pageNumber, Integer pageSize) {
        Page<Event> eventsPage = eventRepository.findEventByCityId(cityId,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "dateEnd")));

        return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(eventsPage.getContent(), cityId), eventsPage);

    }

    @Override
    public EventDetailsResponse getEventById(Long eventId) {

        return Optional.ofNullable(eventRepository.findEventById(eventId))
                .map(eventMapper::eventToEventDetailResponse)
                .orElseThrow(() -> new EntityNotFoundException("entetity with id " + eventId + " not found"));
    }

    @Override
    public PageList<CustomEventResponse> getEventsByFilter(Long userId, FilterRequest filterRequest, Integer pageNumber, Integer pageSize) {
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

            final Set<Long> categoriesIdFromFilter = filterRequest.getCategoriesIdSet();
            Set<Long> categoriesId = categoriesIdFromFilter.isEmpty() ?
                    categoryRepository.findCategoryIdByTagsId(filterRequest.getTagsIdSet()) :
                    categoriesIdFromFilter;
            if (filterRequest.isCity()) {
                final Page<Event> byTagsByCity = eventRepository.findByTagsByCity(filterRequest.getLocationId(),
                        filterRequest.getTagsIdSet(),
                        filterRequest.getStatus(),
                        categoriesId,
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(byTagsByCity.getContent(), filterRequest.getLocationId()), byTagsByCity);

            } else {
                final Page<Event> byTagsByCountry = eventRepository.findByTagsByCountry(filterRequest.getLocationId(),
                        filterRequest.getTagsIdSet(),
                        filterRequest.getStatus(),
                        categoriesId,
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(byTagsByCountry.getContent(), filterRequest.getLocationId()), byTagsByCountry);

            }

        }

        if (!filterRequest.getTagsIdSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            final Set<Long> categoriesIdFromFilter = filterRequest.getCategoriesIdSet();
            Set<Long> categoriesId = categoriesIdFromFilter.isEmpty() ?
                    categoryRepository.findCategoryIdByTagsId(filterRequest.getTagsIdSet()) :
                    categoriesIdFromFilter;
            if (filterRequest.isCity()) {
                final Page<Event> byTagsByVendorsByCity = eventRepository.findByTagsByVendorsByCity(filterRequest.getLocationId(),
                        filterRequest.getVendorsIdSet(),
                        filterRequest.getTagsIdSet(),
                        categoriesId,
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(byTagsByVendorsByCity.getContent(), filterRequest.getLocationId()), byTagsByVendorsByCity);

            } else {
                final Page<Event> byTagsByVendorsByCountry = eventRepository.findByTagsByVendorsByCountry(filterRequest.getLocationId(),
                        filterRequest.getVendorsIdSet(),
                        filterRequest.getTagsIdSet(),
                        categoriesId,
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(byTagsByVendorsByCountry.getContent(), filterRequest.getLocationId()), byTagsByVendorsByCountry);

            }
        }

        if (!filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                filterRequest.getVendorsIdSet().isEmpty()) {

            if (filterRequest.isCity()) {
                final Page<Event> byCategoryByCity = eventRepository.findByCategoryByCity(filterRequest.getLocationId(),
                        filterRequest.getCategoriesIdSet(),
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(byCategoryByCity.getContent(), filterRequest.getLocationId()), byCategoryByCity);

            } else {
                final Page<Event> byCategoryByCountry = eventRepository.findByCategoryByCountry(filterRequest.getLocationId(),
                        filterRequest.getCategoriesIdSet(),
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(byCategoryByCountry.getContent(), filterRequest.getLocationId()), byCategoryByCountry);

            }
        }

        if (!filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            if (filterRequest.isCity()) {
                final Page<Event> byCategoryByVendorsByCity = eventRepository.findByCategoryByVendorsByCity(filterRequest.getLocationId(),
                        filterRequest.getVendorsIdSet(),
                        filterRequest.getCategoriesIdSet(),
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(byCategoryByVendorsByCity.getContent(), filterRequest.getLocationId()), byCategoryByVendorsByCity);

            } else {
                final Page<Event> byCategoryByByVendorsCountry = eventRepository.findByCategoryByByVendorsCountry(filterRequest.getLocationId(),
                        filterRequest.getVendorsIdSet(),
                        filterRequest.getCategoriesIdSet(),
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(byCategoryByByVendorsCountry.getContent(), filterRequest.getLocationId()), byCategoryByByVendorsCountry);

            }
        }

        if (filterRequest.getCategoriesIdSet().isEmpty() &&
                filterRequest.getTagsIdSet().isEmpty() &&
                !filterRequest.getVendorsIdSet().isEmpty()) {

            if (filterRequest.isCity()) {
                final Page<Event> byVendorsByCity = eventRepository.findByVendorsByCity(filterRequest.getLocationId(),
                        filterRequest.getVendorsIdSet(),
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCityId(byVendorsByCity.getContent(), filterRequest.getLocationId()), byVendorsByCity);

            } else {
                final Page<Event> byByVendorsCountry = eventRepository.findByByVendorsCountry(filterRequest.getLocationId(),
                        filterRequest.getVendorsIdSet(),
                        filterRequest.getStatus(),
                        PageRequest.of(pageNumber, pageSize, sort));

                return new PageList<>(eventMapper.eventListToCustomEventResponseListByCountryId(byByVendorsCountry.getContent(), filterRequest.getLocationId()), byByVendorsCountry);

            }
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
