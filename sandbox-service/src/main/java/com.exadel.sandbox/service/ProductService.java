package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    void deleteProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    ProductPagedList listProductsByPartOfName(String productsName, PageRequest pageRequest);

    ProductDto findProductById(Long productId);

    Page<ProductDto> listProducts(PageRequest pageRequest);

    ProductDto saveProduct(ProductDto productDto);

    boolean isCategoryIdUses(Long categoryId);

    boolean isProductNameExists(String productName);
}
