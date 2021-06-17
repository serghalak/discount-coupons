package com.exadel.sandbox.dto;

import lombok.*;
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
