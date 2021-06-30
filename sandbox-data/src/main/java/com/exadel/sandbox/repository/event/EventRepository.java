package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

    @Query("select distinct e from Event e " +
            "join e.locations loc " +
            "where loc.city.id = ?1")
    Page<Event> findEventByCityId(Long cityId, Pageable pageable);

    Event findEventById(Long id);

    @Query("select distinct e from Event e " +
            "join e.tags tag " +
            "join e.locations loc " +
            "join loc.city city " +
            "join city.country country " +
            "where (tag.id in (?2)) " +
            "and country.id = ?1")
    Page<Event> findByTagsByCountry(Long locationId, Set<Long> tagsIsSet, PageRequest of);

    @Query("select distinct e from Event e " +
            "join e.tags tag " +
            "join e.locations loc " +
            "join loc.city city " +
            "where (tag.id in (?2)) " +
            "and city.id = ?1")
    Page<Event> findByTagsByCity(@Param("locationId") Long locationId, @Param("tagsId") Set<Long> tagsIsSet, PageRequest of);

//    @Query(value = "SELECT DISTINCT e.*\n" +
//            "FROM event e\n" +
//            "         JOIN event_tag et ON event_id = id\n" +
//            "         JOIN event_location el ON e.id = el.event_id\n" +
//            "         JOIN location l ON el.location_id = l.id\n" +
//            "         JOIN city c on c.id = l.city_id\n" +
//            "         JOIN vendor v on v.id = e.vendor_id\n" +
//            "WHERE (et.tag_id IN ('%', :tagsId, '%'))\n" +
//            "  AND c.country_id = :locationId\n" +
//            "  AND v.id = ('%', :vendorsId, '%'))", nativeQuery = true)
//    Page<Event> findByTagsByVendorsByCountry(Long locationId, Set<Long> vendorsId, Set<Long> tagsIs, PageRequest of);
//
//    @Query(value = "SELECT DISTINCT e.*\n" +
//            "FROM event e\n" +
//            "         JOIN event_tag et ON event_id = id\n" +
//            "         JOIN event_location el ON e.id = el.event_id\n" +
//            "         JOIN location l ON el.location_id = l.id\n" +
//            "         JOIN vendor v on v.id = e.vendor_id\n" +
//            "WHERE (et.tag_id IN ('%', :tagsId, '%'))\n" +
//            "  AND l.city_id = :locationId" +
//            "AND v.id = ('%', :vendorsId, '%'))", nativeQuery = true)
//    Page<Event> findByTagsByVendorsByCity(Long locationId, Set<Long> vendorsIdSet, Set<Long> tagsIsSet, PageRequest of);
}
