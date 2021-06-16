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

    private ModelMapper mapper;

    public Product productDtoToProduct(ProductDto productDto){

        CategoryDto categoryDto=productDto.getCategoryDto();
        //VendorDto vendorDto=productDto.getVendorDto();

        Category category = Objects.isNull(categoryDto) ? null : mapper.map(categoryDto, Category.class);
        //Vendor vendor=Objects.isNull(vendorDto) ? null : mapper.map(vendorDto,Vendor.class);

        Product product = Objects.isNull(productDto) ? null : mapper.map(productDto, Product.class);

        if(product==null){
            return null;
        }else{
            product.setCategory(category);
            //product.setVendor(vendor);
        }

        return product;

    }

    public ProductDto productToProductDto(Product product){

        Category category=product.getCategory();
        //Vendor vendor=product.getVendor();

        CategoryDto categoryDto = Objects.isNull(category) ? null : mapper.map(category, CategoryDto.class);
        //VendorDto vendorDto=Objects.isNull(vendor) ? null : mapper.map(vendor,VendorDto.class);

        ProductDto productDto = Objects.isNull(product) ? null : mapper.map(product, ProductDto.class);

        if(productDto==null){
            return null;
        }else{
            productDto.setCategoryDto(categoryDto);
            //productDto.setVendorDto(vendorDto);
        }

        return productDto;
    }
}
