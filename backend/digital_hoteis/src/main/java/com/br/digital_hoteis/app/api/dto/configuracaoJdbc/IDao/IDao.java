package com.br.digital_hoteis.app.api.dto.configuracaoJdbc.IDao;

import com.br.digital_hoteis.domain.entity.Category;

import java.util.List;
import java.util.UUID;

public interface IDao <E, I> {
    E create(E entity);

    E findById(UUID id);

    void delete(UUID id);

    E update(UUID id, E entity);

    List<E> findAll();

    List<Category> createCategory(List<Category> categories);

}

