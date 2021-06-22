package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.location.City;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>, EventRepositoryCustom {

    @Query("select e from Event e " +
            "join e.locations loc " +
            "where loc.city = ?1")
    List<Event> findEventByLocations(City city);
    @Transactional
    Event findEventById(Long id);

}
