package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import com.exadel.sandbox.model.location.Location;
import com.exadel.sandbox.model.user.User;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserSavedRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT true FROM saved_event uo " +
            "WHERE event_id=:eventId and user_id =:userId LIMIT 1", nativeQuery = true)
    @Transactional
    BigInteger getOneEventsFromUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);

    @Query("SELECT e FROM Event e " +
            " join  e.userSavedEvents uo  " +
            "WHERE uo.id =?1")
    @Transactional
    List<Event> getAllEventsFromUserSaved(Long id);

    @Modifying
    @Query(value = "delete from saved_event where event_id=:eventId and user_id=:userId ", nativeQuery = true)
    @Transactional
    void deleteFromUserSaved(@Param("eventId") Long eventId, @Param("userId") Long userId);


//    List<Location> getAllEventsLocationsFromSaved(Long userId);
//    value = "select c.id, c.description, c.name from Category c join Event e on e.category_id=c.id " +
//            " join User u  where u.id =?1
//    List<Vendor> getAllVendorsFromSaved(Long userId);
//
}
