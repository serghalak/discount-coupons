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

//        var categoryRequest = productRequest.getCategoryRequest();
//        var vendorRequest = productRequest.getVendorRequest();
//
//
//        var category = Objects.isNull(categoryRequest) ? null : mapper.map(categoryRequest, Category.class);
//        var vendor = Objects.isNull(vendorRequest) ? null : mapper.map(vendorRequest, Vendor.class);
        var product = Objects.isNull(productRequest) ? null : mapper.map(productRequest, Product.class);

        return (product == null) ? null : product ;
//        if (product == null) {
//            return null;
//        } else {
//            product.setCategory(category);
//            product.setVendor(vendor);
//        }
//
//        return product;
    }

    public ProductResponse productToProductResponse(Product product) {

        //var category = product.getCategory();
        //var vendor = product.getVendor();

        //var categoryResponse = Objects.isNull(category) ? null : mapper.map(category, CategoryResponse.class);
        //var vendorResponse = Objects.isNull(vendor) ? null : mapper.map(vendor, VendorResponse.class);
        var productResponse = Objects.isNull(product) ? null : mapper.map(product, ProductResponse.class);

        return (productResponse ==null) ? null : productResponse;

//        if (productResponse == null) {
//            return null;
//        } else {
//            //productResponse.setCategoryResponse(categoryResponse);
//            //productResponse.setVendorResponse(vendorResponse);
//        }
//
//        return productResponse;
    }

}