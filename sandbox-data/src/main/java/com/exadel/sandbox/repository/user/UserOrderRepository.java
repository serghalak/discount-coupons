package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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
    List<Event> getAllEventsFromUserOrder(Long userId);
}
