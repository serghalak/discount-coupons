package com.exadel.sandbox.controllers;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class ProductController {

    private ProductService productService;

    @GetMapping(produces = {"application/json"}, path = "product")
    public ResponseEntity<?> listProducts(){
        log.debug(">>>>List all products");
        List<Product> products = productService.listProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
