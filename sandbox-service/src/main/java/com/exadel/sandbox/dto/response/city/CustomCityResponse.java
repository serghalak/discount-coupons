package com.exadel.sandbox.dto.response.city;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomCityResponse {

    private String cityName;

    private Set<String> addresses = new HashSet<>();

}
