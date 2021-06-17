package com.exadel.sandbox.mappers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.ProductDto;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class ProductMapper {

    private final ModelMapper mapper;

    public Product productDtoToProduct(ProductDto productDto) {

        CategoryDto categoryDto = productDto.getCategoryDto();

        Category category = Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, Category.class);

        Product product = Objects.isNull(productDto) ? null : mapper.map(productDto, Product.class);

        if (product == null) {
            return null;
        } else {
            product.setCategory(category);
        }

        return product;
    }

    public ProductDto productToProductDto(Product product) {

        var category = product.getCategory();

        CategoryDto categoryDto = Objects.isNull(category) ? null : mapper.map(category, CategoryDto.class);

        ProductDto productDto = Objects.isNull(product) ? null : mapper.map(product, ProductDto.class);

        if (productDto == null) {
            return null;
        } else {
            productDto.setCategoryDto(categoryDto);
        }

        return productDto;
    }
}
