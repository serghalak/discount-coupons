package com.exadel.sandbox.mappers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.request.ProductDto;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class ProductMapper {

    private ModelMapper mapper;

    public Product productDtoToProduct(ProductDto productDto){
        return Objects.isNull(productDto) ? null : mapper.map(productDto, Product.class);

    }

    public ProductDto productToProductDto(Product product){
        return Objects.isNull(product) ? null : mapper.map(product,ProductDto.class);
    }
}
