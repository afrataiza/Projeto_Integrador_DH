package br.com.digital_hoteis.model.service;

import br.com.digital_hoteis.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface CategoryService {

    Category createCategory(Category category);
    Category findCategoryById(UUID id);
    Category updateCategoryById(UUID id, Map<String, Object> fields);
    void deleteCategoryById(UUID id);
    Page<Category> listAllCategories(Pageable pageable);

}
