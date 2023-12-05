package com.br.digital_hoteis.domain.exception;

import java.util.UUID;


public class CharacteristicsNotFoundException extends NotFoundException{
    public CharacteristicsNotFoundException(UUID id) {
        super("Characteristics not found! Invalid ID: " + id);
    }
}