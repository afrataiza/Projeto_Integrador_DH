package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.TestCategoryApi;
import com.br.digital_hoteis.app.api.dto.request.CreateCategoryRequest;
import com.br.digital_hoteis.app.api.dto.response.CategoryDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.CategorySummaryResponse;
import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class TestingCategoryController implements TestCategoryApi {

    private final CategoryService categoryService;
    private final ObjectMapper mapper;

    @Autowired
    public TestingCategoryController(CategoryService categoryService, ObjectMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }
    @Override
    public ResponseEntity<Page<CategorySummaryResponse>> findCategories(Pageable pageable) {
        Page<Category> pageCategory = categoryService.findAllCategories(pageable);

        Page<CategorySummaryResponse> response = pageCategory
                .map(category -> new CategorySummaryResponse(
                        category.getId(),
                        category.getDescription()
                ));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CategoryDetailedResponse> createCategory(CreateCategoryRequest request) {
        Category category = mapper.convertValue(request, Category.class);
        Category createdCategory = categoryService.createCategory(category);

        CategoryDetailedResponse categoryDetailedResponse = categoryForCategoryDetailedResponse(createdCategory);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDetailedResponse);
    }

    @Override
    public ResponseEntity<CategoryDetailedResponse> findCategoryById(UUID id) {
        Category category = categoryService.findCategoryById(id);
        CategoryDetailedResponse categoryDetailedResponse = categoryForCategoryDetailedResponse(category);
        return ResponseEntity.ok(categoryDetailedResponse);
    }

    @Override
    public ResponseEntity<CategoryDetailedResponse> updateCategoryById(UUID id, Map<String, Object> fields) {
        Category category = categoryService.updateCategoryById(id, fields);
        CategoryDetailedResponse categoryDetailedResponse = categoryForCategoryDetailedResponse(category);
        return ResponseEntity.accepted().body(categoryDetailedResponse);
    }

    private CategoryDetailedResponse categoryForCategoryDetailedResponse(Category category) {
        return mapper.convertValue(category, CategoryDetailedResponse.class);
    }

    @Override
    public ResponseEntity<Void> deleteCategoryById(UUID id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}
