package com.exadel.sandbox.dto;

import com.exadel.sandbox.model.location.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
public class UserLocationDto {

    private String country;

    private String city;

    private String street;

    private String number;

    private double latitude;

    private double longitude;
}
