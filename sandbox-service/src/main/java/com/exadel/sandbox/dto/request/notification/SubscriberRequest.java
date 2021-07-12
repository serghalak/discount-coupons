package com.exadel.sandbox.dto.request.notification;

import com.exadel.sandbox.model.notification.Subscriber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberRequest {

    private Long id;

    private Subscriber type;

    boolean isSubscribed;

}
