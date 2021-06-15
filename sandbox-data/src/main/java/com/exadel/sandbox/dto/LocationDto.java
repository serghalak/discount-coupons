package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.location.City;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class LocationDto {

    private double latitude;

    private double longitude;

    private String street;

    private String number;

    private City city;
}
