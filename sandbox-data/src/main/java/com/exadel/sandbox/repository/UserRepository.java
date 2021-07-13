package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(final String email);

    @Query(value = "SELECT IF(role = 'ADMIN', 'TRUE', 'FALSE') FROM user WHERE user.id =?1", nativeQuery = true)
    boolean isAdmin(long userId);
}
