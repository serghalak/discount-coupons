package com.exadel.sandbox.repository.notification;

import com.exadel.sandbox.model.notification.Subscription;
import com.exadel.sandbox.model.vendorinfo.Tag;
import com.exadel.sandbox.repository.tag.TagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface SubscriberRepository extends JpaRepository<Subscription, Long> {

    Subscription findByUserIdAndSubscriberIdAndSubscriberType(Long userId, Long subscriberId, String subscriberType);

    Set<Subscription> findAllByUserIdAndSubscriberTypeOrderBySubscriberNameAsc(Long userId, String subscriberType);


}
