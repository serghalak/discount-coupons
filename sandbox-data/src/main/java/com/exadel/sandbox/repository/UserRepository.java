package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(final String email);

    @Modifying
    @Query(value = "insert into user_order(event_id, user_id) values (:eventId, :userId)", nativeQuery = true)
    @Transactional
    void insertIntoUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "insert into saved_event(event_id, user_id) values (:eventId, :userId)", nativeQuery = true)
    @Transactional
    void insertIntoUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "delete from user_order where event_id=:eventId and user_id=:userId ", nativeQuery = true)
    @Transactional
    void deleteFromUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "delete from saved_event where event_id=:eventId and user_id=:userId ", nativeQuery = true)
    @Transactional
    void deleteFromUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Query("SELECT e FROM Event e " +
            " join  e.userOrders uo  " +
            "WHERE uo.id =?1")
    @Transactional
    List<Event> getAllEventsFromUserOrder(@Param("userId") Long userId);


    @Query("SELECT e FROM Event e " +
            " join  e.userSavedEvents uo  " +
            "WHERE uo.id =?1")
    @Transactional
    List<Event> getAllEventsFromUserSaved(Long id);

    @Query(value = "SELECT true FROM user_order uo " +
            "WHERE event_id=:eventId and user_id =:userId LIMIT 1", nativeQuery = true)
    @Transactional
    BigInteger getOneEventsFromUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Query(value = "SELECT true FROM saved_event uo " +
            "WHERE event_id=:eventId and user_id =:userId LIMIT 1", nativeQuery = true)
    @Transactional
    BigInteger getOneEventsFromUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);



}
