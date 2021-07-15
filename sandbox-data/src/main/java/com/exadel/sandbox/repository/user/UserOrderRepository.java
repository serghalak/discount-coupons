package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.statistics.OrderStatisticsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
    Page<Event> getAllEventsFromUserOrder(Long userId, PageRequest of);

    @Query(value = "SELECT  e.id as eventid, e.description as eventdescription, v.name as vendorname\n" +
            "FROM user_order uo\n" +
            "     join event e on uo.event_id = e.id\n" +
            "     join vendor v on e.vendor_id = v.id\n" +
            "      where uo.date_event between ?1  and  ?2", nativeQuery = true)
    List<OrderStatisticsProjection> getAllEventsFromUserOrderForPeriod(LocalDate dateBegin, LocalDate dateEnd);
}
