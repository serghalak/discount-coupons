package com.exadel.sandbox.repository.user;

import com.exadel.sandbox.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(final String email);

    @Query("select u from  User u " +
            "join u.subscriptions s  " +
            "where s.subscriberId=:categoryId and s.subscriberType=:subscriberName")
    Set<User> findAllUsersByCategorySubscription(@Param("categoryId") Long categoryId, @Param("subscriberName") String subscriberName);

    @Query("select u from  User u " +
            "join u.subscriptions s  " +
            "where s.subscriberId=:vendorId and s.subscriberType=:subscriberName")
    Set<User> findAllUsersByVendorSubscription(@Param("vendorId") Long vendorId, @Param("subscriberName") String subscriberName);

    @Query("select u from User u " +
            "join u.subscriptions s " +
            "where s.subscriberId in :ids and s.subscriberType=:subscriberName")
    Set<User> findAllUsersByTagsSubscription(@Param("ids") Set<Long> ids, @Param("subscriberName") String subscriberName);

    @Query(value = "SELECT IF(role = 'ADMIN', 'TRUE', 'FALSE') FROM user WHERE user.id =?1", nativeQuery = true)
    boolean isAdmin(long userId);

    @Query("select u.firstName from User u " +
            "where u.id = ?1")
    String getUsername(Long userId);
}
