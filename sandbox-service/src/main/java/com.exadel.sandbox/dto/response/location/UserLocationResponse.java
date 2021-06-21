package com.exadel.sandbox.dto.response.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLocationResponse {

    private String country;

    private String city;

    private String street;

    private String number;

    private double latitude;

    private double longitude;

}
