package com.exadel.sandbox.mappers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.model.vendorinfo.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class CategoryMapper {

    private ModelMapper mapper;

    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        return Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToCategoryDto(Category category) {
        return Objects.isNull(category) ? null : mapper.map(category, CategoryDto.class);
    }
}
