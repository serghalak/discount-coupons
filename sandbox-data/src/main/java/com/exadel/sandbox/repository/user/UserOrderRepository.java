package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.repository.event.EventProjectionForOrders;
import com.exadel.sandbox.repository.statistics.OrderStatisticsProjection;
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

    @Query(value = "SELECT  e.id as eventId,\n" +
            "       uo.date_event as dateevent,\n" +
            "       e.description as eventDescription,\n" +
            "       v.id as vendorId, v.name as vendorName,\n" +
            "       e.date_end as dateend,\n" +
            "       c.name as cityName, c2.name as countryName\n" +
            "     FROM user_order uo\n" +
            "     join event e on uo.event_id = e.id\n" +
            "     join vendor v on e.vendor_id = v.id\n" +
            "     join event_location el on e.id = el.event_id\n" +
            "     join location l on el.location_id = l.id\n" +
            "    join city c on l.city_id = c.id\n" +
            "    join country c2 on c.country_id = c2.id\n" +
            "     where uo.user_id=?1", nativeQuery = true)
    @Transactional
    List<EventProjectionForOrders> getAllEventsFromUserOrderTest(Long userId);

    @Query(value = "SELECT  e.id as eventid, e.description as eventdescription, v.name as vendorname\n" +
            "FROM user_order uo\n" +
            "     join event e on uo.event_id = e.id\n" +
            "     join vendor v on e.vendor_id = v.id\n" +
            "      where uo.date_event between ?1  and  ?2", nativeQuery = true)
    List<OrderStatisticsProjection> getAllEventsFromUserOrderForPeriod(LocalDate dateBegin, LocalDate dateEnd);
}
