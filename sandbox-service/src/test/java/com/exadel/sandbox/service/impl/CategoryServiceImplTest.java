package com.exadel.sandbox.service.impl;

import com.exadel.sandbox.dto.pagelist.PageList;
import com.exadel.sandbox.dto.request.category.CategoryRequest;
import com.exadel.sandbox.dto.response.category.CategoryResponse;
import com.exadel.sandbox.mappers.category.CategoryMapper;
import com.exadel.sandbox.model.vendorinfo.Category;
import com.exadel.sandbox.model.vendorinfo.Event;
import com.exadel.sandbox.model.vendorinfo.Tag;
import com.exadel.sandbox.repository.category.CategoryRepository;
import com.exadel.sandbox.service.exceptions.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_FIELD_SORT = "name";

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private CategoryRequest categoryRequest;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("some category");
        category.setDescription("some description");
    }

    @Test
    void shouldDeleteExistsCategoryByIdAndEmptyEvent() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));

        categoryService.deleteCategoryById(1L);

        verify(categoryRepository, times(1)).deleteById(1L);

    }

    @Test
    void shouldThrowExceptionDuringDeleteNoExistsCategoryById() {

        when(categoryRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(null));

        RuntimeException exception = assertThrows(EntityNotFoundException.class,
                () -> {
                    categoryService.deleteCategoryById(2L);
                });
    }

    @Test
    void shouldThrowExceptionDuringDeleteExistsCategoryByIdAndExistsEvent() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));

        Event event = new Event();
        category.setEvents(new HashSet<Event>(Arrays.asList(event)));

        RuntimeException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    categoryService.deleteCategoryById(1L);
                });
    }

    @Test
    void shouldThrowExceptionDuringUpdateCategoryIfNameIsNull() {
        when(categoryRequest.getName()).thenReturn(null);

        RuntimeException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    categoryService.updateCategory(categoryRequest);
                });
    }

    @Test
    void shouldThrowExceptionDuringUpdateCategoryIfNameIsEmpty() {
        when(categoryRequest.getName()).thenReturn("");

        RuntimeException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    categoryService.updateCategory(categoryRequest);
                });
    }

    @Test
    void shouldThrowExceptionDuringUpdateCategoryIfCategoryIsNotPresent() {

        when(categoryRequest.getName()).thenReturn("name");
        when(categoryRequest.getId()).thenReturn(1L);
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(null));

        RuntimeException exception = assertThrows(EntityNotFoundException.class,
                () -> {
                    categoryService.updateCategory(categoryRequest);
                });
    }

    @Test
    void shouldSaveCategoryOneTimeDuringUpdateCategoryIfCategoryIsPresent() {

        category.setTags(new HashSet<Tag>(Arrays.asList(new Tag())));

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(1L);
        categoryRequest.setName("name");

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));
        when(categoryMapper.categoryRequestToCategory(categoryRequest)).thenReturn(category);
        categoryService.updateCategory(categoryRequest);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void listCategoriesByPartOfNameIfCategoryNameIsEmpty() {

        PageImpl<Category> categoryPage =
                new PageImpl<>(Arrays.asList(category),
                        PageRequest.of(0, 5, Sort.by(DEFAULT_FIELD_SORT).ascending()), 1);
        when(categoryRepository.findAllByNameContainingIgnoreCaseOrderByNameAsc(anyString(), any()))
                .thenReturn(categoryPage);
        PageList<CategoryResponse> categoryResponsePageList =
                categoryService.listCategoriesByPartOfName("part", DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        assertNotNull(categoryResponsePageList);

    }

    @Test
    void shouldThrowExceptionIfCategoryIsNotPresentCategoryById() {

        when(categoryRepository.findById(2L)).thenReturn(java.util.Optional.ofNullable(null));

        RuntimeException exception = assertThrows(EntityNotFoundException.class,
                () -> {
                    categoryService.findCategoryById(2L);
                });
    }

    @Test
    void shouldReturnCategoryResponseIfCategoryIsPresentCategoryById() {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(1L);
        categoryResponse.setName("name");

        category.setTags(new HashSet<Tag>(Arrays.asList(new Tag())));

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));
        when(categoryMapper.categoryToCategoryResponse(category)).thenReturn(categoryResponse);

        CategoryResponse categoryById = categoryService.findCategoryById(1L);
        CategoryResponse categoryExpected = new CategoryResponse();
        categoryExpected.setId(1L);
        categoryExpected.setName("name");
        assertEquals(categoryExpected.getName(), categoryById.getName());
        assertEquals(categoryExpected.getId(), categoryById.getId());

    }

    @Test
    void shouldThrowExceptionFindCategoryByIdIfCategoryNotFound() {

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(null));

        RuntimeException exception = assertThrows(EntityNotFoundException.class,
                () -> {
                    categoryService.findCategoryById(1L);
                });
    }

    @Test
    void shouldNotReturnNullFindCategoryByIdIfCategoryFound() {

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(1L);
        categoryResponse.setName("name");

        category.setTags(new HashSet<Tag>(Arrays.asList(new Tag())));

        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(category));
        when(categoryMapper.categoryToCategoryResponse(category)).thenReturn(categoryResponse);

        CategoryResponse categoryById = categoryService.findCategoryById(1L);
        assertNotNull(categoryById);

    }

    @Test
    void listCategories() {
        PageImpl<Category> categoryPage =
                new PageImpl<>(Arrays.asList(category),
                        PageRequest.of(0, 5, Sort.by(DEFAULT_FIELD_SORT).ascending()), 1);
        when(categoryRepository.findAllByOrderByNameAsc(any())).thenReturn(categoryPage);
        PageList<CategoryResponse> categoryResponsePageList = categoryService.listCategories(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        assertNotNull(categoryResponsePageList);
    }

    @Test
    void shouldThrowExceptionDuringSaveCategoryIfNameIsEmpty() {
        when(categoryRequest.getName()).thenReturn("");

        RuntimeException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    categoryService.saveCategory(categoryRequest);
                });
    }

    @Test
    void shouldThrowExceptionDuringSaveCategoryIfNameIsNull() {
        when(categoryRequest.getName()).thenReturn(null);

        RuntimeException exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    categoryService.saveCategory(categoryRequest);
                });
    }

    @Test
    void shouldThrowExceptionDuringSaveCategoryIfNameIsExist() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setName("name");

        when(categoryRepository.findByName("name")).thenReturn(category);

        RuntimeException exception = assertThrows(DuplicateNameException.class,
                () -> {
                    categoryService.saveCategory(categoryRequest);
                });

    }

    @Test
    void shouldSaveCategoryDuringSaveCategoryIfNameIsNotExist() {

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setId(1L);
        categoryRequest.setName("name");

        when(categoryRepository.findByName("name")).thenReturn(null);
        when(categoryMapper.categoryRequestToCategory(categoryRequest)).thenReturn(category);
        categoryService.saveCategory(categoryRequest);
        verify(categoryRepository, times(1)).saveAndFlush(category);

    }

    @Test
    void shouldCategoryNameNotExists() {
        when(categoryRepository.findByName("name")).thenReturn(null);

        assertNull(categoryRepository.findByName("name"));
    }

    @Test
    void shouldCategoryNameExists() {
        when(categoryRepository.findByName("name")).thenReturn(category);

        assertNotNull(categoryRepository.findByName("name"));
    }

}