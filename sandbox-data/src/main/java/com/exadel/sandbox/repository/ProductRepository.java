package com.exadel.sandbox.repository;

import com.exadel.sandbox.model.vendorinfo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//    @Override
//    List<Product> findAll();

    List<Product>findAllByCategoryId(Long categoryId);
}
