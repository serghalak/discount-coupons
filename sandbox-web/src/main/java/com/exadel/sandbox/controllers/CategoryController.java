package com.exadel.sandbox.controllers;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(produces = {"application/json"}, path = "category")
    public ResponseEntity<?>listCategories(){
        log.debug(">>>>List all categories");
        List<Category> categories = categoryService.listCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
