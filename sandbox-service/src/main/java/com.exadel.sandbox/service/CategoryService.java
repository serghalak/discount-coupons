package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.model.vendorinfo.Category;

import java.util.List;

public interface CategoryService {

    List<Category> listCategories();

    CategoryDto saveCategory(CategoryDto categoryDto);
}
