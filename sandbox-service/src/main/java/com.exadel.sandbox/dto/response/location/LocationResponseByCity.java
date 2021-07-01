package com.exadel.sandbox.dto.response.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
//for first Main Page
public class LocationResponseByCity {

    private String cityName;

    private String countryName;

    private Set<String> addresses;

}
