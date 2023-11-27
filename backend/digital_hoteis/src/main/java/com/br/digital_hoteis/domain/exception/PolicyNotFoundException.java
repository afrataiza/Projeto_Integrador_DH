package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class PolicyNotFoundException extends NotFoundException {
    public PolicyNotFoundException(UUID id) {
        super("Policy not found! Invalid ID: " + id);
    }
}
