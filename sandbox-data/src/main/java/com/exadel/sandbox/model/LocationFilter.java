package com.exadel.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationFilter {
    long countryId;
    String countryName;
    long cityId;
    String cityName;
}
