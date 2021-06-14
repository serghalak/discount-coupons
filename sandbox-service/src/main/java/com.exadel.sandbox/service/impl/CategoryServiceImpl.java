package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {

        return categoryRepository.findAll();
    }
}
