package com.exadel.sandbox.dto.request.notification;

import com.exadel.sandbox.model.notification.SubscriberEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberRequest {

    private Long subscriberId;

    private String subscriberType;

    private String subscriberName;

    private boolean isSubscribed;

}
