package com.exadel.sandbox.service.vendor.mappers;

import com.exadel.sandbox.model.vendorinfo.Vendor;
import com.exadel.sandbox.service.vendor.dto.VendorDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class VendorMapper {
    private ModelMapper mapper;

    public Vendor toEntity(VendorDto dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Vendor.class);

    }

    public VendorDto toDto(Vendor vendor){
        return Objects.isNull(vendor) ? null : mapper.map(vendor, VendorDto.class);
    }
}
