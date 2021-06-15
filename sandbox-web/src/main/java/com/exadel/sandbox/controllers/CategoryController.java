package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.service.CategoryService;
import com.exadel.sandbox.ui.mappers.UICategoryMapper;
import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.ui.request.CategoryRequest;
import com.exadel.sandbox.ui.response.CategoryResponse;
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
public class CategoryController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 3;
    private static final String DEFAULT_FIELD_SORT="name";
    private CategoryService categoryService;
    private UICategoryMapper uiCategoryMapper;


    @GetMapping(produces = {"application/json"}, path = "categoryname/{name}")
    public ResponseEntity<CategoryPagedList> getCategoriesByPartOfName(
            @PathVariable("name") String categoryName
            ,@RequestParam(value = "pageNumber", required = false) Integer pageNumber
            ,@RequestParam(value = "pageSize", required = false) Integer pageSize){

        log.debug(">>>>>>>>>>Category List by part of name: " + categoryName);

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        CategoryPagedList categoryList = categoryService.listCategoriesByPartOfName(categoryName,
                PageRequest.of(
                        pageNumber
                        , pageSize
                        , Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


    @GetMapping(produces = {"application/json"}, path = "category/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("categoryId") Long categoryId ){

        log.debug(">>>>>>getCategoryById " + categoryId);
        CategoryDto categoryDto = categoryService.findCategoryById(categoryId);
        CategoryResponse categoryResponse = uiCategoryMapper.categoryDtoToCategoryResponse(categoryDto);

        return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
    }


    @GetMapping(produces = {"application/json"}, path = "category")
    public ResponseEntity<CategoryPagedList> listCategories(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        log.debug(">>>>List all categories");

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        CategoryPagedList categoryList = categoryService.listCategories(
                PageRequest.of(
                        pageNumber
                        , pageSize
                        , Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"}
            , consumes = {"application/json"}
            , path = "category/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        CategoryDto categoryDto = uiCategoryMapper.categoryRequestToCategoryDto(categoryRequest);

        CategoryDto savedCategoryDto = categoryService.saveCategory(categoryDto);

        CategoryResponse categoryResponse = uiCategoryMapper.categoryDtoToCategoryResponse(savedCategoryDto);

        return new ResponseEntity(categoryResponse, HttpStatus.OK);

    }



}
