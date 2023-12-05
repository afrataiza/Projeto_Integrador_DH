package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class ReviewScoreNotFoundException extends NotFoundException {
    public ReviewScoreNotFoundException(UUID id) {
        super("Review Score not found! Invalid ID: " + id);
    }
}