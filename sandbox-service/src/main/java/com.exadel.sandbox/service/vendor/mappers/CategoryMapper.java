package com.exadel.sandbox.service.vendor.mappers;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.service.vendor.dto.CategoryDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class CategoryMapper {
    private ModelMapper mapper;

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, Category.class);

    }

    public CategoryDto categoryToCategoryDto(Category category){
        return Objects.isNull(category) ? null : mapper.map(category,CategoryDto.class);
    }
}
