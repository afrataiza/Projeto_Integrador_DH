package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface CategoryService {

    Page<Category> findAllCategories(Pageable pageable);
    Category createCategory(Category category);
    Category findCategoryById(UUID id);
    Category updateCategoryById(UUID id, Map<String, Object> fields);
    void deleteCategoryById(UUID id);
    Page<Category> listAllCategories(Pageable pageable);

}
