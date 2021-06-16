package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.ProductDto;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.service.ProductService;
import com.exadel.sandbox.ui.mappers.UIProductMapper;
import com.exadel.sandbox.ui.request.CategoryRequest;
import com.exadel.sandbox.ui.request.ProductRequest;
import com.exadel.sandbox.ui.response.CategoryResponse;
import com.exadel.sandbox.ui.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class ProductController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 3;
    private static final String DEFAULT_FIELD_SORT = "name";

    private ProductService productService;
    private UIProductMapper uiProductMapper;

    @DeleteMapping(path = {"product/{productId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long productId){

        log.debug(">>>>>>>>>>controller delete product by Id");
        log.debug(">>>>>>>>this method is not implemented yet");
        //productService.deleteProductById(productId);
    }

    @PutMapping(path ={"product/{productId}"}, produces = {"application/json"})
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("productId") Long productId
            , @Valid @RequestBody ProductRequest productRequest) {

        if (productRequest.getName() == null || productRequest.getName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ProductDto productDto = uiProductMapper.productRequestToProductDto(productRequest);
        ProductDto updateProduct = productService.updateProduct(productId, productDto);
        ProductResponse productResponse = uiProductMapper.productDtoToProductResponse(updateProduct);

        return new ResponseEntity<>(productResponse, HttpStatus.OK);

    }


    @GetMapping(produces = {"application/json"}, path = "productname/{name}")
    public ResponseEntity<ProductPagedList> getProductsByPartOfName(
            @PathVariable("name") String productName
            , @RequestParam(value = "pageNumber", required = false) Integer pageNumber
            , @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        log.debug(">>>>>>>>>>Product List by part of name: " + productName);

        pageNumber=getPageNumber(pageNumber);
        pageSize=getPageSize(pageSize);


        ProductPagedList productList = productService.listProductsByPartOfName(productName,
                PageRequest.of(
                        pageNumber
                        , pageSize
                        , Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }


    @GetMapping(produces = {"application/json"}, path = "product")
    public ResponseEntity<ProductPagedList> listProducts(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize  ){


        log.debug(">>>>List all products");

        pageNumber=getPageNumber(pageNumber);
        pageSize=getPageSize(pageSize);

        ProductPagedList productList = productService.listProducts(
                PageRequest.of(
                        pageNumber
                        , pageSize
                        , Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    //-----------------------------------------------------------
    private int getPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 0) {
            return DEFAULT_PAGE_NUMBER;
        }
        return pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            return DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }
}
