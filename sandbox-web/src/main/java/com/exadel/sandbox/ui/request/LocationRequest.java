package com.exadel.sandbox.ui.request;

import com.exadel.sandbox.model.location.City;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class LocationRequest {

    private double latitude;

    private double longitude;

    private String street;

    private String number;

    private City city;
}
