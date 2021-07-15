package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.repository.statistics.SavedStatisticProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

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

    @Query(value = "SELECT  e.id as eventId, e.description as eventDescription,v.name as vendorName\n" +
            "FROM saved_event si\n" +
            "    join event e on si.event_id = e.id\n" +
            "    join vendor v on e.vendor_id = v.id\n" +
            "    where si.saved_date between ?1  and  ?2", nativeQuery = true)
    List<SavedStatisticProjection> getAllEventsFromUserSavedForPeriod(LocalDate dateBegin, LocalDate dateEnd);

}
