package br.com.digital_hoteis.model.service.impl;

import br.com.digital_hoteis.model.entity.Category;
import br.com.digital_hoteis.model.exception.CategoryNotFoundException;
import br.com.digital_hoteis.model.repository.CategoryRepository;
import br.com.digital_hoteis.model.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;

@Slf4j
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ObjectMapper mapper;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category updateCategoryById(UUID id, Map<String, Object> fields) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        Category input = mapper.convertValue(fields, Category.class);
        fields.forEach((property, value) -> {
            Field field = findField(Category.class, property);
            if (field == null) {
                log.error("field not found on the payload! '%s', I'll ignore it.".formatted(property));
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, category, newValue);
        });
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
    }

    @Override
    public Page<Category> listAllCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }


}
