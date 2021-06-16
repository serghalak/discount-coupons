package com.exadel.sandbox.ui.mappers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.request.ProductDto;
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

    private ModelMapper mapper;

    public ProductResponse productDtoToCategoryResponse(ProductDto productDto){
        return Objects.isNull(productDto) ? null : mapper.map(productDto, ProductResponse.class);

    }

    public ProductDto productRequestToProductDto(ProductRequest productRequest){
        return Objects.isNull(productRequest) ? null : mapper.map(productRequest,ProductDto.class);
    }
}
