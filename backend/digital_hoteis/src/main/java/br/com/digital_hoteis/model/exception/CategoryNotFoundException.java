package br.com.digital_hoteis.model.exception;

import java.util.UUID;

public class CategoryNotFoundException extends NotFoundException{

    public CategoryNotFoundException(UUID id) {
        super("Category not found! Invalid ID: " + id);
    }
}
