package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(final String email);

    User findByUsername(final String username);
    @Modifying
    @Query(value = "insert into user_order(event_id, user_id) values (:eventId, :userId);", nativeQuery = true)
    void insertUserOrder(@Param("eventId") Long eventId, @Param("userId") Long userId);


}
