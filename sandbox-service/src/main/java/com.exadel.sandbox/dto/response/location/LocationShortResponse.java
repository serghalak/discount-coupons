package com.exadel.sandbox.dto.response.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationShortResponse {

    private String city;

    private String country;

}