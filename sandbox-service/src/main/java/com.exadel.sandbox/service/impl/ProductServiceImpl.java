package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.ProductDto;
import com.exadel.sandbox.mappers.CategoryMapper;
import com.exadel.sandbox.mappers.ProductMapper;
import com.exadel.sandbox.mappers.VendorMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.repository.ProductRepository;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private CategoryMapper categoryMapper;
    private VendorMapper vendorMapper;

    @Override
    public void deleteProductById(Long productId) {

        log.debug(">>>>>>>>delete product id " + productId);
        //to do if this product uses in event we cannot delete the product!!!
        productRepository.deleteById(productId);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {

        log.debug(">>>>>update product with id: " + productId);

        Optional<Product> productOptional = productRepository.findById(productId);

        productOptional.ifPresentOrElse(product -> {
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setLink(productDto.getLink());
            product.setCategory(categoryMapper.categoryDtoToCategory(
                    productDto.getCategoryDto()));
            product.setVendor(vendorMapper.vendorDtoToVendor(
                    productDto.getVendorDto()));
            productRepository.save(product);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. ProductId: "
                    + productId);
        });

        Optional<Product> updateProduct=productRepository.findById(productId);
        if(updateProduct.isPresent()){
            return productMapper.productToProductDto(updateProduct.get());
        }
        return null;
    }

    @Override
    public ProductPagedList listProductsByPartOfName(String productName, PageRequest pageRequest) {

        log.debug(">>>>>>>>>>>>>ListCategoryByPartOfName ...." + productName);

        ProductPagedList productPagedList;
        Page<Product> productPage;
        productPage = productRepository.findAll(pageRequest);
        productPagedList=new ProductPagedList(productPage
                .getContent()
                .stream()
                .filter(product ->  product.getName().matches("(.*)" + productName + "(.*)"))//add search use only part of category name
                .map(productMapper::productToProductDto)
                .collect(Collectors.toList()),
                PageRequest
                        .of(productPage.getPageable().getPageNumber(), productPage.getPageable().getPageSize()),
                productPage.getTotalElements()  );

        return productPagedList;
    }

    @Override
    public ProductDto findProductById(Long productId) {
        return null;
    }

    @Override
    public ProductPagedList listProducts(PageRequest pageRequest) {
        return null;
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        return null;
    }
}
