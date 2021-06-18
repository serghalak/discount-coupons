package com.exadel.sandbox.mappers.vendor;

import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class VendorMapper {

    private ModelMapper mapper;

    public Vendor vendorRequestToVendor(VendorRequest vendorRequest) {
        return Objects.isNull(vendorRequest) ? null : mapper.map(vendorRequest, Vendor.class);

    }

    public VendorResponse vendorToVendorResponse(Vendor vendor) {
        return Objects.isNull(vendor) ? null : mapper.map(vendor, VendorResponse.class);
    }

}