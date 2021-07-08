package com.exadel.sandbox.dto.request.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterRequest {

    Long locationId;

    Boolean isCountry;

    List<Long> categories;

    List<Long> vendors;

    List<Long> tags;

    String main;
}
