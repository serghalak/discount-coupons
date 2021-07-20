package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.EventFilterRequest;
import com.exadel.sandbox.dto.request.event.EventRequest;
import com.exadel.sandbox.dto.response.event.CustomEventResponse;
import com.exadel.sandbox.dto.response.event.EventDetailsResponse;
import com.exadel.sandbox.mappers.event.EventMapper;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Status;
import com.exadel.sandbox.model.vendorinfo.Tag;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.repository.event.EventRepository;
import com.exadel.sandbox.repository.event.specification.SpecificationBuilder;
import com.exadel.sandbox.repository.location_repository.CityRepository;
import com.exadel.sandbox.repository.location_repository.LocationRepository;
import com.exadel.sandbox.repository.tag.TagRepository;
import com.exadel.sandbox.repository.user.UserRepository;
import com.exadel.sandbox.repository.vendor.VendorRepository;
import com.exadel.sandbox.service.EventService;
import com.exadel.sandbox.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;
    private final EventMapper eventMapper;

    private final UserService userService;

    private final UserRepository userRepository;
    private final SpecificationBuilder specificationBuilder;

    @Override
    public PageList<EventDetailsResponse> getAll(Integer pageNumber, Integer pageSize) {
        final Page<Event> eventsPage = eventRepository.findAll(PageRequest.of(getPageNumber(pageNumber),
                getPageSize(pageSize), Sort.by("id").descending()));

        return new PageList<>(eventMapper.eventListToDetailEventResponse(eventsPage.getContent()), eventsPage);
    }

    @Override
    public PageList<CustomEventResponse> getAllEventsByUserId(Long userId, Integer pageNumber, Integer pageSize) {
        var city = cityRepository.findCityByUserId(userId);
        return getEventResponsesByCityAndStatus(city.getId(), List.of(Status.ACTIVE, Status.PERPETUAL),
                Sort.by(Sort.Direction.DESC, "dateEnd"), getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<CustomEventResponse> getAllEventsByCityId(Long cityId, Integer pageNumber, Integer pageSize) {
        return getEventResponsesByCityAndStatus(cityId, List.of(Status.ACTIVE, Status.PERPETUAL),
                Sort.by(Sort.Direction.DESC, "dateEnd"), getPageNumber(pageNumber), getPageSize(pageSize));
    }

    @Override
    public PageList<?> getAllEventsByDescription(Long userId, Long cityId, String search,
                                                 Integer pageNumber, Integer pageSize) {

        return getAllEventsByDescriptionNotAdmin(userId, cityId, search, pageNumber, pageSize);
    }

    @Override
    public PageList<EventDetailsResponse> getAllEventsByDescriptionForAdmin(String search, Integer pageNumber, Integer pageSize) {
        return getAllEventsByDescriptionIsAdmin(search, pageNumber, pageSize);
    }

    private PageList<CustomEventResponse> getEventResponsesByCityAndStatus(Long cityId, List<Status> statuses, Sort sort, Integer pageNumber, Integer pageSize) {

        Page<Event> eventsPage = eventRepository.findEventByCityIdAndStatuses(cityId,
                statuses,
                PageRequest.of(pageNumber, pageSize, sort));

        return new PageList<>(eventMapper.
                eventListToCustomEventResponseListByCityId(eventsPage.getContent(), cityId), eventsPage);
    }

    @Override
    public EventDetailsResponse getEventById(Long eventId) {
        return Optional.ofNullable(eventRepository.findEventById(eventId))
                .map(eventMapper::eventToEventDetailResponse)
                .orElseThrow(() -> new EntityNotFoundException("Event with id " + eventId + " not found"));
    }

    @Override
    public PageList<CustomEventResponse> getEventsByFilter(Long userId, EventFilterRequest eventFilterRequest,
                                                           Integer pageNumber, Integer pageSize) {


        var sort = getSorting(eventFilterRequest.getStatus(), eventFilterRequest.getSortingType());
        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);

        if (eventFilterRequest.getLocationId() == 0) {
            var city = cityRepository.findCityByUserId(userId);
            eventFilterRequest.setLocationId(city.getId());
            eventFilterRequest.setCity(true);
        }

        final Page<Event> aNew = specificationBuilder.getEventsByParameters(
                List.of(eventFilterRequest.getStatus()),
                eventFilterRequest.getLocationId(),
                eventFilterRequest.isCity(),
                eventFilterRequest.getCategoriesIdSet(),
                eventFilterRequest.getTagsIdSet(),
                eventFilterRequest.getVendorsIdSet(),
                PageRequest.of(pageNumber, pageSize, sort));

        return eventFilterRequest.isCity() ?
                new PageList<>(
                        eventMapper.eventListToCustomEventResponseListByCityId(aNew.getContent(),
                                eventFilterRequest.getLocationId()), aNew) :
                new PageList<>(
                        eventMapper.eventListToCustomEventResponseListByCountryId(aNew.getContent(),
                                eventFilterRequest.getLocationId()), aNew);
    }

    private Sort getSorting(Status status, EventFilterRequest.SortingType sortingType) {

        switch (status) {
            case COMING_SOON:
                return Sort.by(Sort.Direction.ASC, "dateBegin");
            case EXPIRED:
                return Sort.by(Sort.Direction.DESC, "name");
            default:
                return getSortBySortingType(sortingType);
        }
    }

    private Sort getSortBySortingType(EventFilterRequest.SortingType sortingType) {
        switch (sortingType) {
            case HOTEST:
                return Sort.by(Sort.Direction.ASC, "dateEnd");
            case NEWEST:
                return Sort.by(Sort.Direction.DESC, "dateBegin");
            default:
                return Sort.by(Sort.Direction.DESC, "viewedUsersEvents");
        }
    }

    @Override
    public boolean deleteEventById(Long eventId) {
        final var event = eventRepository.getById(eventId);

        if (checkRightForRemove(event)) return false;

        eventRepository.delete(event);

        return true;
    }

    private boolean checkRightForRemove(Event event) {
        return !event.getUserFeedbacks().isEmpty() ||
                !event.getUserOrders().isEmpty() ||
                !event.getUserSavedEvents().isEmpty();
    }

    @Override
    public ResponseEntity<?> createEvent(EventRequest eventRequest) {

        return getResponseEntity(null, eventRequest);
    }

    @Override
    public ResponseEntity<?> updateEvent(Long eventId, EventRequest eventRequest) {

        if (eventId == null || eventId <= 0) {
            return ResponseEntity.badRequest().build();
        }

        if (!eventRepository.existsById(eventId)) {
            return ResponseEntity.badRequest().build();
        }

        return getResponseEntity(eventId, eventRequest);
    }

    private ResponseEntity<?> getResponseEntity(Long eventId, EventRequest eventRequest) {
        final Set<Location> locationsById = locationRepository.getLocationsById(eventRequest.getLocationsId());
        final Set<Tag> tagsById = tagRepository.getTagsById(eventRequest.getTagsId());
        final var vendor = vendorRepository.getById(eventRequest.getVendorId());

        if (locationsById.isEmpty() || tagsById.isEmpty() || vendor == null) {
            return ResponseEntity.badRequest().build();
        }

        final List<Tag> tags = tagsById.stream()
                .filter(tag -> !tag.getCategory().getId().equals(eventRequest.getCategoryId()))
                .collect(Collectors.toList());

        if (!tags.isEmpty()) {
            return ResponseEntity.badRequest().body("Tag's don't agree with the inputted category");
        }

        final var category = categoryRepository.getById(eventRequest.getCategoryId());
        var event = eventMapper.eventRequestToEvent(eventRequest, vendor, locationsById, category, tagsById);

        if (eventId != null) {
            event.setId(eventId);
        }

        event = eventRepository.save(event);

        return ResponseEntity.ok().body(eventMapper.eventToEventDetailResponse(event));
    }

    public PageList<CustomEventResponse> getAllEventsByDescriptionNotAdmin(Long userId, Long cityId, String search,
                                                                           Integer pageNumber, Integer pageSize) {

        cityId = cityId == null ? cityRepository.findCityByUserId(userId).getId() : cityId;

        Page<Event> eventsPage = eventRepository.findEventByDescription(("%" + search + "%"),
                cityId, PageRequest.of(getPageNumber(pageNumber), getPageSize(pageSize),
                        Sort.by(Sort.Direction.DESC, "dateEnd")));
        return new PageList<>(eventMapper.
                eventListToCustomEventResponseListByCityId(eventsPage.getContent(), cityId), eventsPage);
    }

    public PageList<EventDetailsResponse> getAllEventsByDescriptionIsAdmin(String search,
                                                                           Integer pageNumber, Integer pageSize) {

        Page<Event> eventsPage = eventRepository.findEventByDescriptionIsAdmin(("%" + search + "%"),
                PageRequest.of(getPageNumber(pageNumber), getPageSize(pageSize),
                        Sort.by(Sort.Direction.DESC, "dateEnd")));
        return new PageList<>(eventMapper.eventListToDetailEventResponse(eventsPage.getContent()), eventsPage);

    }

    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }

}
