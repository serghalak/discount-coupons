package com.exadel.sandbox.mappers.product;

import com.exadel.sandbox.dto.request.product.ProductRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.product.ProductResponse;
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

    public Product productRequestToProduct(ProductRequest productRequest) {
        var categoryRequest = productRequest.getCategoryRequest();

        var category = Objects.isNull(categoryRequest) ? null : mapper.map(categoryRequest, Category.class);

        var product = Objects.isNull(productRequest) ? null : mapper.map(productRequest, Product.class);

        if (product == null) {
            return null;
        } else {
            product.setCategory(category);
        }

        return product;
    }

    public ProductResponse productToProductResponse(Product product) {

        var category = product.getCategory();

        var categoryResponse = Objects.isNull(category) ? null : mapper.map(category, CategoryResponse.class);

        var productResponse = Objects.isNull(product) ? null : mapper.map(product, ProductResponse.class);

        if (productResponse == null) {
            return null;
        } else {
            productResponse.setCategoryResponse(categoryResponse);
        }

        return productResponse;
    }

}