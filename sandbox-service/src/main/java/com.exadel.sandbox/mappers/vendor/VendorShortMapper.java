package com.exadel.sandbox.mappers.vendor;

import com.exadel.sandbox.dto.response.filter.CategoryFilterResponse;
import com.exadel.sandbox.dto.response.filter.VendorFilterResponse;
import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class VendorShortMapper {

    private ModelMapper mapper;

    public VendorShortResponse vendorToVendorShortResponse(Vendor vendor) {
        return Objects.isNull(vendor) ? null : mapper.map(vendor, VendorShortResponse.class);
    }



}
