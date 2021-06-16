package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.ProductDto;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.repository.ProductRepository;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public void deleteProductById(Long productId) {
        return;
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        return null;
    }

    @Override
    public ProductPagedList listProductsByPartOfName(String productsName, PageRequest pageRequest) {
        return null;
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
