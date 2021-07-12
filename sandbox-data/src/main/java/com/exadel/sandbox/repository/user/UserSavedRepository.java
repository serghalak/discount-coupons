package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
public interface UserSavedRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "insert into saved_event(event_id, user_id) " +
            "values (:eventId, :userId)",
            nativeQuery = true)
    @Transactional
    void insertIntoUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Modifying
    @Query(value = "delete from saved_event where event_id=:eventId and user_id=:userId ", nativeQuery = true)
    @Transactional
    void deleteFromUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);

}
