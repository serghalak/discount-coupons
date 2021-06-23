package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.product.ProductRequest;
import com.exadel.sandbox.dto.response.product.ProductResponse;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class ProductController {

    private final ProductService productService;

    @DeleteMapping(path = {"product/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long productId) {

        log.debug(">>>>>>>>>>controller delete product by Id");
        log.debug(">>>>>>>>this method is not implemented yet");
        //productService.deleteProductById(productId);
    }

    @PutMapping(path = {"product/{productId}"}, produces = {"application/json"})
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") Long productId,
                                                         @Valid @RequestBody ProductRequest productRequest) {

        var updateProduct = productService.updateProduct(productId, productRequest);

        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "productname/{name}")
    public ResponseEntity<ProductPagedList> getProductsByPartOfName(
            @PathVariable("name") String productName,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize) {

        log.debug(">>>>>>>>>>Product List by part of name: " + productName);

        ProductPagedList productList =
                productService.listProductsByPartOfName(productName, pageNumber, pageSize);
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "product/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("productId") Long productId) {

        log.debug(">>>>>>getProductById " + productId);

        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "product")
    public ResponseEntity<ProductPagedList> listProducts(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize) {

        log.debug(">>>>List all products");

        return new ResponseEntity<>(productService.listProducts(pageNumber,pageSize), HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "product/")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {

        return new ResponseEntity<>(productService.saveProduct(productRequest), HttpStatus.OK);
    }


}