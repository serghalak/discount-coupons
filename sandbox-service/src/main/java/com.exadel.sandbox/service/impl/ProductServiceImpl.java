package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.pagelist.ProductPagedList;
import com.exadel.sandbox.dto.request.product.ProductRequest;
import com.exadel.sandbox.dto.response.product.ProductResponse;
import com.exadel.sandbox.mappers.category.CategoryMapper;
import com.exadel.sandbox.mappers.product.ProductMapper;
import com.exadel.sandbox.mappers.vendor.VendorMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Product;
import com.exadel.sandbox.repository.ProductRepository;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final VendorMapper vendorMapper;

    @Override
    public void deleteProductById(Long productId) {
        log.debug(">>>>>>>>delete product id " + productId);
        //to do if this product uses in event we cannot delete the product!!!
        productRepository.deleteById(productId);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        log.debug(">>>>>update product with id: " + productId);

        if (productRequest.getName() == null || productRequest.getName().equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE
                    , "Product name cannot be empty");
        }

        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            return productMapper.productToProductResponse(
                    productRepository.save(
                            productMapper.productRequestToProduct(productRequest)));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. ProductId: "
                    + productId);
        }
    }

    @Override
    public ProductPagedList listProductsByPartOfName(String productName, int pageNumber, int pageSize) {
        log.debug(">>>>>>>>>>>>>ListCategoryByPartOfName ...." + productName);

        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);

        Page<Product> productPage = productRepository.findAllByNameContainingIgnoreCase(productName
                , PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ProductPagedList(productPage
                .getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList()),
                PageRequest.of(
                        productPage.getPageable().getPageNumber(),
                        productPage.getPageable().getPageSize(),
                        productPage.getPageable().getSort()),
                productPage.getTotalElements());
    }

    @Override
    public ProductResponse findProductById(Long productId) {
        log.debug(">>>>>>ProductService find product by Id: " + productId);

        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            log.debug(">>>>>Product is found: " + productId);
            return productMapper.productToProductResponse(product.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + productId);
        }
    }

    @Override
    public ProductPagedList listProducts(int pageNumber, int pageSize) {
        log.debug(">>>>>>>>>>>>>ListProducts....");

        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);

        Page<Product> productPage = productRepository.findAll(
                PageRequest.of(
                        pageNumber,
                        pageSize,
                        Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ProductPagedList(productPage
                .getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList()),
                PageRequest.of(
                        productPage.getPageable().getPageNumber(),
                        productPage.getPageable().getPageSize(),
                        productPage.getPageable().getSort()),
                productPage.getTotalElements());
    }

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        String productName = productRequest.getName();

        if (productName == null || productName.equals("")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "The product cannot be empty");
        }

        if (isProductNameExists(productName)) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The category: "
                    + productName + " is already exists");
        }

        Product savedProduct = productRepository.save(
                productMapper.productRequestToProduct(productRequest));

        return productMapper.productToProductResponse(savedProduct);
    }

    @Override
    public boolean isCategoryIdUses(Long categoryId) {
        return !productRepository.findAllByCategoryId(categoryId).isEmpty();
    }

    @Override
    public boolean isProductNameExists(String productName) {
        return productRepository.findByName(productName) != null;
    }

    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}
