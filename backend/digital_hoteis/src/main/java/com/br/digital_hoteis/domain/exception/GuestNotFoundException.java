package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class GuestNotFoundException  extends NotFoundException {
    public GuestNotFoundException(UUID id) {
        super("Guest not found! Invalid ID: " + id);
    }
}
