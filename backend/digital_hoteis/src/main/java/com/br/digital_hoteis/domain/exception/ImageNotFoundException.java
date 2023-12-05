package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class ImageNotFoundException extends NotFoundException{

    public ImageNotFoundException(UUID id) {
        super("Image not found! Invalid ID: " + id);
    }
}
