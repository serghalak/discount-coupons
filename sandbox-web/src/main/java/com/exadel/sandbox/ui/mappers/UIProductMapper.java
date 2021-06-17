package com.exadel.sandbox.ui.mappers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.ProductDto;
import com.exadel.sandbox.ui.request.CategoryRequest;
import com.exadel.sandbox.ui.request.ProductRequest;
import com.exadel.sandbox.ui.response.CategoryResponse;
import com.exadel.sandbox.ui.response.ProductResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class UIProductMapper {

    private final ModelMapper mapper;

    public ProductResponse productDtoToProductResponse(ProductDto productDto) {

        CategoryDto categoryDto = productDto.getCategoryDto();

        CategoryResponse categoryResponse = Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, CategoryResponse.class);

        ProductResponse productResponse = Objects.isNull(productDto) ? null : mapper.map(productDto, ProductResponse.class);

        if (productResponse == null) {
            return null;
        } else {
            productResponse.setCategoryResponse(categoryResponse);
        }

        return productResponse;
    }

    public ProductDto productRequestToProductDto(ProductRequest productRequest) {

        CategoryRequest categoryRequest = productRequest.getCategoryRequest();

        CategoryDto categoryDto = Objects.isNull(categoryRequest) ? null : mapper.map(categoryRequest, CategoryDto.class);

        ProductDto productDto = Objects.isNull(productRequest) ? null : mapper.map(productRequest, ProductDto.class);

        if (productDto == null) {
            return null;
        } else {
            productDto.setCategoryDto(categoryDto);
        }

        return productDto;
    }
}
