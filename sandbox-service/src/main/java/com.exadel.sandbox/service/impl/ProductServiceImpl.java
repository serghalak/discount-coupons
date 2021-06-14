package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.repository.ProductRepository;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Override
    public List<Product> listProducts() {

        return productRepository.findAll();
    }
}
