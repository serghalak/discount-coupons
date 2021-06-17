package com.exadel.sandbox.service.vendor.dto;

import com.exadel.sandbox.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorFullDto {
    private VendorDto vendorDto;
    private List<EventDto> eventDtos;
    private List<CategoryDto> categoryDtos;
}
