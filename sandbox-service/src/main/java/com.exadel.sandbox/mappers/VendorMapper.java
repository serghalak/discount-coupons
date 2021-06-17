package com.exadel.sandbox.mappers;

import com.exadel.sandbox.dto.VendorDto;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class VendorMapper {

    private ModelMapper mapper;

    public Vendor vendorDtoToVendor(VendorDto vendorDto){
        return Objects.isNull(vendorDto) ? null : mapper.map(vendorDto, Vendor.class);

    }

    public VendorDto vendorToVendorDto(Vendor vendor){
        return Objects.isNull(vendor) ? null : mapper.map(vendor,VendorDto.class);
    }

    public com.exadel.sandbox.service.vendor.dto.VendorDto toDto(Vendor vendor){
        return Objects.isNull(vendor) ? null : mapper.map(vendor, com.exadel.sandbox.service.vendor.dto.VendorDto.class);
    }
}
