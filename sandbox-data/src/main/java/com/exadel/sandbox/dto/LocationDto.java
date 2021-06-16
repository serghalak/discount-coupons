package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.BaseEntity;
import lombok.*;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class LocationDto extends BaseEntity {

    private double latitude;

    private double longitude;

    private String street;

    private String number;

    private CityDto city;
}
