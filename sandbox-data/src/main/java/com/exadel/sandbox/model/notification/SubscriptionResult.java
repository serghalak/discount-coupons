package com.exadel.sandbox.model.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResult {

    private Long subscriberId;

    private String subscriberName;

    boolean isSubscribed;
}
