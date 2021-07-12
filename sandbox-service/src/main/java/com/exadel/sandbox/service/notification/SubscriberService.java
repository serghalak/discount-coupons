package com.exadel.sandbox.service.notification;

import com.exadel.sandbox.dto.request.filter.FilterRequest;
import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.filter.FilterResponse;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;

public interface SubscriberService {
    SubscriberResponse getVendorSubscriberResponse(SubscriberRequest subscriberRequest, Long userId);
}
