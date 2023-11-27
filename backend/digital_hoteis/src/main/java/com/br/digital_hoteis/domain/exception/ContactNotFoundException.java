package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class ContactNotFoundException extends NotFoundException {
    public ContactNotFoundException(UUID id) {
        super("Contact not found! Invalid ID: " + id);
    }
}
