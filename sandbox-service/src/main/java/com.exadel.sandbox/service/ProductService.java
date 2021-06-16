package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.ProductDto;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    void deleteProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    ProductPagedList listProductsByPartOfName(String productsName, PageRequest pageRequest);

    ProductDto findProductById(Long productId);

    ProductPagedList listProducts(PageRequest pageRequest);

    ProductDto saveProduct(ProductDto productDto);

    boolean isCategoryIdUses(Long categoryId);

    boolean isProductNameExists(String productName);
}
