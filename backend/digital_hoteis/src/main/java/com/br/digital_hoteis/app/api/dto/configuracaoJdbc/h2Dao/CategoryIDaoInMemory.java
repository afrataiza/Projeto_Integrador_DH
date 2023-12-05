package com.br.digital_hoteis.app.api.dto.configuracaoJdbc.h2Dao;

import com.br.digital_hoteis.app.api.dto.configuracaoJdbc.IDao.IDao;
import com.br.digital_hoteis.domain.entity.Category;

import java.util.List;
import java.util.UUID;

public class CategoryIDaoInMemory implements IDao<Category, UUID> {

    private List<Category> categoryRepository;

    public CategoryIDaoInMemory(List<Category> categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(Category category) {
        categoryRepository.add(category);
        return category;
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository
                .stream()
                .filter(category -> category
                        .getId()
                        .equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.removeIf(category -> category.getId().equals(id));
    }

    @Override
    public Category update(UUID id, Category category) {
        for (int i = 0; i < categoryRepository.size(); i++) {
            Category newCategory = categoryRepository.get(i);
            if (category.getId().equals(id)) {
                categoryRepository.set(i, category);
                return category;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository;
    }

    @Override
    public List<Category> createCategory(List<Category> categories) {
        categoryRepository.addAll(categories);
        return categories;
    }
}

