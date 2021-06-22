package com.exadel.sandbox.dto.response.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationShortResponse {

    private String cityName;

    private String countryName;

}