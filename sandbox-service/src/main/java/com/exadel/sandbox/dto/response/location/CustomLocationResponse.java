package com.exadel.sandbox.dto.response.location;

import com.exadel.sandbox.dto.response.city.CustomCityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomLocationResponse {

    private String countryName;

    private Set<CustomCityResponse> cities = new HashSet<>();

}
