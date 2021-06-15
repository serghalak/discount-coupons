package com.exadel.sandbox.service;

import com.exadel.sandbox.dto.CategoryDto;
import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.model.vendorinfo.Category;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {

    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    CategoryPagedList listCategoriesByPartOfName(String categoryName, PageRequest pageRequest);

    CategoryDto findCategoryById(Long categoryId);

    CategoryPagedList listCategories(PageRequest pageRequest);

    CategoryDto saveCategory(CategoryDto categoryDto);
}
