package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface UserViewedEventRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "insert into viewed_event(event_id, user_id, date_event) " +
            "values (:eventId, :userId, :dateEvent)", nativeQuery = true)
    @Transactional
    void insertIntoViewed(@Param("eventId") Long eventId, @Param("userId") Long userId,
                          @Param("dateEvent") LocalDateTime dateEvent);

    @Query(value = "select exists (select date_event from viewed_event " +
            "where user_id = :userId and event_id = :eventId)", nativeQuery = true)
    Long existsIntoViewed(@Param("eventId") Long eventId, @Param("userId") Long userId);

}
