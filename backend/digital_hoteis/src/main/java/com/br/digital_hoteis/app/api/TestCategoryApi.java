package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.request.CreateCategoryRequest;
import com.br.digital_hoteis.app.api.dto.response.CategoryDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.CategorySummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

public interface TestCategoryApi {
    ResponseEntity<Page<CategorySummaryResponse>> findCategories(Pageable pageable);

    ResponseEntity<CategoryDetailedResponse> createCategory(CreateCategoryRequest request);

    ResponseEntity<CategoryDetailedResponse> findCategoryById(UUID id);

    ResponseEntity<CategoryDetailedResponse> updateCategoryById(UUID id, Map<String, Object> fields);

    ResponseEntity<Void> deleteCategoryById(UUID id);
}