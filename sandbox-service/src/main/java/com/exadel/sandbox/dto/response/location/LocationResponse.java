package com.exadel.sandbox.dto.response.location;

import com.exadel.sandbox.dto.response.city.CityResponse;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class LocationResponse {

    private Long id;

    private double latitude;

    private double longitude;

    private String street;

    private String number;

    private CityResponse city;

}