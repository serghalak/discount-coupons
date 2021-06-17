package com.exadel.sandbox.ui.mappers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.ui.request.CategoryRequest;
import com.exadel.sandbox.ui.response.CategoryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class UICategoryMapper {

    private final ModelMapper mapper;

    public CategoryResponse categoryDtoToCategoryResponse(CategoryDto categoryDto){
        return Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, CategoryResponse.class);
    }

    public CategoryDto categoryRequestToCategoryDto(CategoryRequest categoryRequest){
        return Objects.isNull(categoryRequest) ? null : mapper.map(categoryRequest,CategoryDto.class);
    }
}
