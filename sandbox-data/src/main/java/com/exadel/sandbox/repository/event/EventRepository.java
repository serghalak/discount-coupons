package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

    //    @Query("select e from Event e " +
//            "join e.locations loc " +
//            "where loc.city = ?1")
//    List<Event> findEventByLocations(City city);
    @Query("select distinct e from Event e " +
            "join e.locations loc " +
            "where loc.city.id = ?1")
    Page<Event> findEventByCityId(Long cityId, Pageable pageable);

    Event findEventById(Long id);

}
