package com.exadel.sandbox.dto.response.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterResponse {

    private List<LocationFilterResponse> locations;

    private List<CategoryFilterResponse> categories;

    private List<TagFilterResponse> tags;

    private List<VendorFilterResponse> vendors;

}
