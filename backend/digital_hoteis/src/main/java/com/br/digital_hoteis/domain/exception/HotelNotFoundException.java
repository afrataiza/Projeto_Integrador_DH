package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class HotelNotFoundException extends NotFoundException{

    public HotelNotFoundException(UUID id) {
        super("Hotel not found! Invalid ID: " + id);
    }
}
