package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.service.CategoryService;
import com.exadel.sandbox.ui.mappers.UICategoryMapper;
import com.exadel.sandbox.ui.request.CategoryRequest;
import com.exadel.sandbox.ui.response.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private static final Integer DEFAULT_PAGE_SIZE = 2;

    private CategoryService categoryService;
    private UICategoryMapper uiCategoryMapper;

    @GetMapping(produces = {"application/json"}, path = "category")
    public ResponseEntity<?> listCategories() {
        log.debug(">>>>List all categories");
        List<Category> categories = categoryService.listCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
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
