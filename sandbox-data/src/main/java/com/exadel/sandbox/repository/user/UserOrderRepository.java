package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.event.EventProjectionForOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public interface UserOrderRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "insert into user_order(event_id, user_id, date_event) " +
            "values (:eventId, :userId, :dateEvent)", nativeQuery = true)
    @Transactional
    void insertIntoUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId,
                             @Param("dateEvent") LocalDateTime dateEvent);

    @Modifying
    @Query(value = "delete from user_order where event_id=:eventId and user_id=:userId ",
            nativeQuery = true)
    @Transactional
    void deleteFromUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Query("SELECT e FROM Event e " +
            " join  e.userOrders uo  " +
            "WHERE uo.id =?1")
    @Transactional
    Page<Event> getAllEventsFromUserOrder(Long userId, PageRequest of);

    @Query( value = "SELECT uo.date_event as dateevent, e.* FROM event e " +
            "left join user_order uo on e.id = uo.event_id " +
            "where user_id=?1", nativeQuery = true)
    @Transactional
    Page<EventProjectionForOrders> getAllEventsFromUserOrderWithDate(Long userId, PageRequest of);
}

//@Query("SELECT DISTINCT  com.exadel.sandbox.model.vendorinfo.Event.builder()" +
//        ".id(e.id)" +
//        ".dateOfCreation(date_event) " +
//        ".description(e.description) " +
//        ".vendor(v) " +
//        ".dateEnd(e.date_end) " +
//        ".locations(l.location_id) " +
//        ".build() " +
//        "FROM event e " +
//        "join user_order uo on e.id = uo.event_id " +
//        "join vendor v on v.id = e.vendor_id " +
//        "join event_location l on l.event_id = e.id " +
//        "where user_id=?1")
