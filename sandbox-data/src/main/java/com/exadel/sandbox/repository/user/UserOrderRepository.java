package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Query(value = "SELECT u.* FROM user u " +
            "join user_order uo on u.id = uo.user_id " +
            "join event e on uo.event_id=e.id " +
            "WHERE category_id=:categoryId", nativeQuery = true)
    Set<User> findAllUsersByCategoryFavorite(@Param("categoryId") Long categoryId);

    @Query(value = "SELECT u.* FROM user u " +
            "join user_order uo on u.id = uo.user_id " +
            "join event e on uo.event_id=e.id " +
            "WHERE vendor_id=:vendorId", nativeQuery = true)
    Set<User> findAllUsersByVendorFavorite(@Param("vendorId") Long vendorId);

    @Query("select u from User u " +
            "join u.usersOrder uo " +
            "join uo.tags t " +
            "where (t.id in (?1))")
    Set<User> findAllUsersByTagsFavorite(Set<Long> ids);
}
