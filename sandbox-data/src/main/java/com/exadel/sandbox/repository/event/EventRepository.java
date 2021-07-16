package com.exadel.sandbox.repository.event;

import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom, JpaSpecificationExecutor<Event> {

    /*TODO To determine what method are the best*/
//    Page<Event> findDistinctByLocationsCityIdAndStatus(Long cityId, Status status, Pageable pageable);

    @Query("select distinct e from Event e " +
            "join e.locations loc " +
            "where loc.city.id = ?1 " +
            "and e.status = ?2")
    Page<Event> findEventByCityIdAndStatus(Long cityId, Status status, Pageable pageable);

    @Query("select distinct e from Event e " +
            "join e.locations loc " +
            "where loc.city.id = ?2 AND (e.description like ?1 or e.fullDescription like ?1)")
    Page<Event> findEventByDescription(String search, Long cityId, Pageable pageable);

    @Query("select distinct e from Event e " +
            "join e.locations loc " +
            "where (e.description like ?1 or e.fullDescription like ?1)")
    Page<Event> findEventByDescriptionIsAdmin(String search, Pageable pageable);

    @Query(value = "SELECT e.* FROM event e " +
            "join saved_event se on e.id = se.event_id " +
            "WHERE event_id=:eventId and user_id =:userId LIMIT 1", nativeQuery = true)
    Event getOneEventsFromUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Query(value = "SELECT e.* FROM event e " +
            "join user_order uo on e.id = uo.event_id " +
            "WHERE event_id=:eventId and user_id =:userId LIMIT 1", nativeQuery = true)
    Event getOneEventsFromUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Query(value = "SELECT e.* FROM event e join saved_event se on e.id = se.event_id WHERE  user_id =?1", nativeQuery = true)
    @Transactional
    Page<Event> getAllEventsFromUserSaved(Long userId, PageRequest of);

    Event findEventById(Long id);

    @Query("select distinct e from Event e " +
            "join e.locations loc " +
            "where loc.city.id = ?1 " +
            "and e.status in ?2")
    Page<Event> findEventByCityIdAndStatuses(Long cityId, List<Status> statuses, PageRequest of);
}
