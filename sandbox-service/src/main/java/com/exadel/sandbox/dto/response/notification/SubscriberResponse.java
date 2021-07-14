package com.exadel.sandbox.dto.response.notification;

import com.exadel.sandbox.model.notification.SubscriberEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberResponse {

    private Long subscriberId;

    //private String subscriberType;

    private String subscriberName;

    boolean isSubscribed;
}
