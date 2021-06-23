package com.exadel.sandbox.mappers.product;

import com.exadel.sandbox.dto.request.event.EventRequest;
import com.exadel.sandbox.dto.request.product.ProductRequest;
import com.exadel.sandbox.dto.request.vendor.VendorRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.dto.response.product.ProductResponse;
import com.exadel.sandbox.dto.response.vendor.VendorResponse;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.model.vendorinfo.Vendor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@Component
public class ProductMapper {

    private final ModelMapper mapper;

    public Product productRequestToProduct(ProductRequest productRequest) {

        var product = Objects.isNull(productRequest) ? null : mapper.map(productRequest, Product.class);

        return (product == null) ? null : product ;

    }

    public ProductResponse productToProductResponse(Product product) {

       var productResponse = Objects.isNull(product) ? null : mapper.map(product, ProductResponse.class);

        return (productResponse ==null) ? null : productResponse;

    }

}