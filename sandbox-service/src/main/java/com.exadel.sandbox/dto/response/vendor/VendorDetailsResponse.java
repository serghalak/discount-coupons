package com.exadel.sandbox.dto.response.vendor;

import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.dto.response.event.EventShortResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDetailsResponse {

    private VendorResponse vendorResponse;

    private List<EventShortResponse> eventResponse;

    private List<CategoryShortResponse> categoryResponse;

}