package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.product.ProductRequest;
import com.exadel.sandbox.dto.response.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

    void deleteProductById(Long productId);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    ProductPagedList listProductsByPartOfName(String productsName, int pageNumber, int pageSize);

    ProductResponse findProductById(Long productId);

    ProductPagedList listProducts(int pageNumber, int pageSize);

    ProductResponse saveProduct(ProductRequest productRequest);

    boolean isCategoryIdUses(Long categoryId);

    boolean isProductNameExists(String productName);

}
