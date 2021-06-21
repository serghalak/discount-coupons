package com.exadel.sandbox.controllers;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class CategoryController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 3;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final CategoryService categoryService;


    @DeleteMapping(path = {"category/{categoryId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {

        log.debug(">>>>>>>>>>controller delete category by Id");

        categoryService.deleteCategoryById(categoryId);
    }

    @PutMapping(path = {"category/{categoryId}"}, produces = {"application/json"})
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("categoryId") Long categoryId,
                                                           @Valid @RequestBody CategoryRequest categoryRequest) {

        if (categoryRequest.getName() == null || categoryRequest.getName().equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var updateCategory = categoryService.updateCategory(categoryId, categoryRequest);

        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @GetMapping(produces = {"application/json"}, path = "categoryname/{name}")
    public ResponseEntity<CategoryPagedList> getCategoriesByPartOfName(
            @PathVariable("name") String categoryName,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        log.debug(">>>>>>>>>>Category List by part of name: " + categoryName);

        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);

        CategoryPagedList categoryList = categoryService.listCategoriesByPartOfName(categoryName,
                PageRequest.of(pageNumber, pageSize, Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }


    @GetMapping(produces = {"application/json"}, path = "category/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("categoryId") Long categoryId) {

        log.debug(">>>>>>getCategoryById " + categoryId);

        var category = categoryService.findCategoryById(categoryId);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }


    @GetMapping(produces = {"application/json"}, path = "category")
    public ResponseEntity<CategoryPagedList> listCategories(
            @RequestParam(value = "pageNumber", required = false , defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "0") Integer pageSize) {

        log.debug(">>>>List all categories");

        return new ResponseEntity<>(categoryService.listCategories(pageNumber,pageSize), HttpStatus.OK);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"},
            path = "category/create")
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        String categoryName = categoryRequest.getName();

        if (categoryName == null || categoryName.equals("")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (categoryService.isCategoryNameExists(categoryName)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        var savedCategory = categoryService.saveCategory(categoryRequest);

        return new ResponseEntity<>(savedCategory, HttpStatus.OK);
    }

    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }

}
