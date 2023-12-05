package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class HostNotFoundException extends NotFoundException {
    public HostNotFoundException(UUID id) {
        super("Host not found! Invalid ID: " + id);
    }
}
