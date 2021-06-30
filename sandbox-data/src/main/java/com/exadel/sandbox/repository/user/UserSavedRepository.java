package com.exadel.sandbox.repository.user;

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

    @Query("SELECT e FROM Event e " +
            " join  e.userSavedEvents uo  " +
            "WHERE uo.id =?1")
    @Transactional
    List<Event> getAllEventsFromUserSaved(Long id);

//    List<Location> getAllEventsLocationsFromSaved(Long userId);
//
//    List<Vendor> getAllVendorsFromSaved(Long userId);
//
//    List<Category> getAllCategoriesFromSaved(Long userId);
}
