package com.exadel.sandbox.repository.event.specification;

import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Status;
import com.exadel.sandbox.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SpecificationBuilder {

    private final EventRepository eventRepository;

    public Page<Event> getEventsByParameters(List<Status> status,
                                             Long cityId,
                                             boolean isCity,
                                             Set<Long> categoriesId,
                                             Set<Long> tagsId,
                                             Set<Long> vendorsId,
                                             Pageable pageable) {
        final Specification<Event> initSpecification = Specification.where(statusLike(status).and(distinct()));

        return eventRepository.findAll(getSpecByFilterParameters(initSpecification, cityId, isCity,
                categoriesId, tagsId, vendorsId), pageable);
    }

    private Specification<Event> getSpecByFilterParameters(Specification<Event> specification,
                                                           Long locationId,
                                                           boolean isCity,
                                                           Set<Long> categoriesId,
                                                           Set<Long> tagsId,
                                                           Set<Long> vendorsId) {
        specification = isCity ?
                specification.and(cityLike(locationId)) : specification.and(countryLike(locationId));

        if (tagsId.isEmpty()) {
            specification = categoriesId.isEmpty() ?
                    specification : specification.and(categoryLike(categoriesId));
        } else {
            specification = specification.and(tagsLike(tagsId));
        }

        if (!vendorsId.isEmpty()) {
            specification = specification.and(vendorsLike(vendorsId));
        }

        return specification;
    }

    public static Specification<Event> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }

    private Specification<Event> statusLike(List<Status> statuses) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(
                        root.get("status"))
                        .value(statuses);
    }

    private Specification<Event> cityLike(Long cityId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.join("locations")
                                .join("city")
                                .get("id"),
                        cityId);
    }

    private Specification<Event> countryLike(Long countryId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.join("locations")
                                .join("city")
                                .join("country")
                                .get("id"),
                        countryId);
    }

    private Specification<Event> categoryLike(Set<Long> categorysId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(
                        root.join("category")
                                .get("id"))
                        .value(categorysId);
    }

    private Specification<Event> tagsLike(Set<Long> tagsId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(
                        root.join("tags")
                                .get("id"))
                        .value(tagsId);
    }

    private Specification<Event> vendorsLike(Set<Long> vendorsId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(
                        root.join("vendor")
                                .get("id"))
                        .value(vendorsId);
    }

}
