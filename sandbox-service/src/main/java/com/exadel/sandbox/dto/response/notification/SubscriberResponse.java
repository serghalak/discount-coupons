package com.exadel.sandbox.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberResponse {

    private Long id;

    private String name;

    private boolean isSubscribed;
}
