package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {

        return categoryRepository.findAll();
    }
}
