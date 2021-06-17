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

    private ModelMapper mapper;


    public ProductResponse productDtoToProductResponse(ProductDto productDto){

        CategoryDto categoryDto=productDto.getCategoryDto();
        //VendorDto vendorDto=productDto.getVendorDto();

        CategoryResponse categoryResponse=Objects.isNull(categoryDto) ? null : mapper.map(categoryDto,CategoryResponse.class);
        //VendorResponse vendorResponse=Objects.isNull(vendorDto) ? null : mapper.map(vendorDto,VendorResponse.class);

        ProductResponse productResponse=Objects.isNull(productDto) ? null : mapper.map(productDto,ProductResponse.class) ;

        if(productResponse==null){
            return null;
        }else{
            productResponse.setCategoryResponse(categoryResponse);
            //productResponse.setVendorResponse(vendorResponse);
        }

        return productResponse;

    }

    public ProductDto productRequestToProductDto(ProductRequest productRequest){

        CategoryRequest categoryRequest=productRequest.getCategoryRequest();
        //VendorRequest vendorRequest=productRequest.getVendorRequest();

        CategoryDto categoryDto=Objects.isNull(categoryRequest) ? null : mapper.map(categoryRequest,CategoryDto.class);
        //VendorDto vendorDto=Objects.isNull(vendorRequest) ? null : mapper.map(vendorRequest,VendorDto.class);

        ProductDto productDto=Objects.isNull(productRequest) ? null : mapper.map(productRequest,ProductDto.class) ;

        if(productDto==null){
            return null;
        }else{
            productDto.setCategoryDto(categoryDto);
            //productDto.setVendorDto(vendorDto);
        }

        return productDto;

    }
}
