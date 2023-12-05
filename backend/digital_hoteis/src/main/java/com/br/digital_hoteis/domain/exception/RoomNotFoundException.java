package com.br.digital_hoteis.domain.exception;

import java.util.UUID;

public class RoomNotFoundException extends NotFoundException{
    public RoomNotFoundException(UUID id) {
        super("Room not found! Invalid ID: " + id);
    }
}
