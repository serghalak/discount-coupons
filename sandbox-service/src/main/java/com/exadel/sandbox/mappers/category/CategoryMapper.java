package com.exadel.sandbox.mappers.category;

import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.filter.CategoryFilterResponse;
import com.exadel.sandbox.model.vendorinfo.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class CategoryMapper {

    private ModelMapper mapper;

    public CategoryResponse categoryToCategoryResponse(Category category) {
        return Objects.isNull(category) ? null : mapper.map(category, CategoryResponse.class);
    }

    public Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        return Objects.isNull(categoryRequest) ? null : mapper.map(categoryRequest, Category.class);
    }

    public CategoryFilterResponse categoryToCategoryFilterResponse(Category category){
        return Objects.isNull(category) ? null : mapper.map(category, CategoryFilterResponse.class);
    }

}
