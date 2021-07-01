package com.exadel.sandbox.dto.request.country;

import com.exadel.sandbox.model.location.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRequest {

    private String name;

    private Set<City> cities;
}
