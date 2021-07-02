package com.exadel.sandbox.mappers.category;

import com.exadel.sandbox.dto.response.category.CategoryShortResponse;
import com.exadel.sandbox.model.vendorinfo.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class CategoryShortMapper {

    private ModelMapper mapper;

    public CategoryShortResponse categoryToCategoryShortResponse(Category category) {
        return Objects.isNull(category) ? null : mapper.map(category, CategoryShortResponse.class);
    }
}
