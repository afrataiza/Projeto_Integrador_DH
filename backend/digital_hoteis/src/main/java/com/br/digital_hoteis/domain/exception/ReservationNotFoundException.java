package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class ReservationNotFoundException extends NotFoundException {
    public ReservationNotFoundException(UUID id) {
        super("Reservation not found! Invalid ID: " + id);
    }
}
