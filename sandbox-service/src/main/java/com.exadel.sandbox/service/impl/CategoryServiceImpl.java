package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.CategoryPagedList;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.mappers.category.CategoryMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.service.CategoryService;
import com.exadel.sandbox.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {


    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 3;
    private static final String DEFAULT_FIELD_SORT = "name";

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductService productService;

    @Override
    public void deleteCategoryById(Long categoryId) {

        log.debug(">>>>>>>>delete category id " + categoryId);

        if (productService.isCategoryIdUses(categoryId)) {
            categoryRepository.deleteById(categoryId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,
                    "You cannot delete category. Category is uses");
        }
    }

    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {

        log.debug(">>>>>update category with id: " + categoryId);

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);


        categoryOptional.ifPresentOrElse(category -> {
            category.setName(categoryRequest.getName());
            category.setDescription(categoryRequest.getDescription());
            categoryRepository.save(category);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. CategoryId: " + categoryId);
        });

        Optional<Category> updateCategory = categoryRepository.findById(categoryId);
        return updateCategory.map(categoryMapper::categoryToCategoryResponse).orElse(null);
    }

    @Override
    public CategoryPagedList listCategoriesByPartOfName(String categoryName, PageRequest pageRequest) {

        log.debug(">>>>>>>>>>>>>ListCategoryByPartOfName ...." + categoryName);

        CategoryPagedList categoryPagedList;
        Page<Category> categoryPage;
        categoryPage = categoryRepository.findAll(pageRequest);
        categoryPagedList = new CategoryPagedList(categoryPage
                .getContent()
                .stream()
                .filter(category -> category.getName().matches("(.*)" + categoryName + "(.*)"))//add search use only part of category name
                .map(categoryMapper::categoryToCategoryResponse)
                .collect(Collectors.toList()),
                PageRequest
                        .of(categoryPage.getPageable().getPageNumber(), categoryPage.getPageable().getPageSize()),
                categoryPage.getTotalElements());

        return categoryPagedList;
    }

    @Override
    public CategoryResponse findCategoryById(Long categoryId) {

        log.debug(">>>>>>CategoryService find by Id: " + categoryId);

        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            log.debug(">>>>>Category is found: " + categoryId);
            return categoryMapper.categoryToCategoryResponse(category.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Category");
        }

    }

    @Override
    public CategoryPagedList listCategories(int pageNumber, int pageSize) {

        log.debug(">>>>>>>>>>>>>ListCategory ....");

        pageNumber = getPageNumber(pageNumber);
        pageSize = getPageSize(pageSize);

        Page<Category> categoryPage=categoryRepository.findAll(
                PageRequest.of(
                        pageNumber
                        , pageSize
                        , Sort.by(DEFAULT_FIELD_SORT).ascending()));

        return  new CategoryPagedList(categoryPage
                .getContent()
                .stream()
                .map(categoryMapper::categoryToCategoryResponse)
                .collect(Collectors.toList()),
                    PageRequest
                        .of(categoryPage.getPageable().getPageNumber()
                                , categoryPage.getPageable().getPageSize()
                                ,categoryPage.getPageable().getSort()),
                    categoryPage.getTotalElements());

    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        var category = categoryMapper.categoryRequestToCategory(categoryRequest);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(savedCategory);
    }

    @Override
    public boolean isCategoryNameExists(String categoryName) {
        Category categoryByName = categoryRepository.findByName(categoryName);

        return categoryByName != null;
    }



    private int getPageNumber(Integer pageNumber) {
        return pageNumber == null || pageNumber < 0 ? DEFAULT_PAGE_NUMBER : pageNumber;
    }

    private int getPageSize(Integer pageSize) {
        return pageSize == null || pageSize < 1 ? DEFAULT_PAGE_SIZE : pageSize;
    }
}