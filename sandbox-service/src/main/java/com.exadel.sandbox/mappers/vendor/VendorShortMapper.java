package com.exadel.sandbox.mappers.vendor;

import com.exadel.sandbox.dto.response.vendor.VendorShortResponse;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class VendorShortMapper {

    private ModelMapper mapper;

    public VendorShortResponse vendorToVendorShortResponse(Vendor vendor) {
        return Objects.isNull(vendor) ? null : mapper.map(vendor, VendorShortResponse.class);
    }

    public List<VendorShortResponse> listTagToListTagResponse(List<Vendor> vendors) {
        if (vendors == null || vendors.isEmpty()) {
            return Collections.emptyList();
        }

        return vendors.stream()
                .map(this::vendorToVendorShortResponse)
                .collect(Collectors.toList());
    }
}
