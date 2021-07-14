package com.exadel.sandbox.mappers.notification;

import com.exadel.sandbox.dto.request.notification.SubscriberRequest;
import com.exadel.sandbox.dto.response.notification.SubscriberResponse;
import com.exadel.sandbox.model.notification.Subscription;
import com.exadel.sandbox.model.notification.SubscriptionResult;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class SuscriberMapper {

    private final ModelMapper mapper;

    public SubscriberResponse subscriptionToSubscriberResponse(Subscription subscription) {
        return Objects.isNull(subscription) ? null : mapper.map(subscription, SubscriberResponse.class);
    }

    public Subscription subscriberRequestToSubscription(SubscriberRequest subscriberRequest) {
        return Objects.isNull(subscriberRequest) ? null : mapper.map(subscriberRequest, Subscription.class);
    }

    public SubscriberResponse subscriptionResultToSubscriberResponse(SubscriptionResult subscriptionResult) {
        return Objects.isNull(subscriptionResult) ? null : mapper.map(subscriptionResult, SubscriberResponse.class);
    }
}
