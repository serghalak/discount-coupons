package com.exadel.sandbox.service.notification;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.filter.FilterResponse;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;
import com.exadel.sandbox.model.notification.SubscriberEnum;

import java.util.List;

public interface SubscriberService {

    boolean saveSubscription(SubscriberRequest subscriberRequest, Long userId);

    List<SubscriberResponse> getAllVendorSubscriber(Long userId, SubscriberEnum subscriberType);

    List<SubscriberResponse> getAllCategorySubscriber(Long userId, SubscriberEnum subscriberType);

    List<SubscriberResponse> getAllTagSubscriber(Long userId, SubscriberEnum subscriberType);

    boolean createEmailNotificationUsersBySubscription(Long eventId);
}
