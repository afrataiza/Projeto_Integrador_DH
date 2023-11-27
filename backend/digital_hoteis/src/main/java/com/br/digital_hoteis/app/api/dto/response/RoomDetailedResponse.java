package com.br.digital_hoteis.app.api.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record RoomDetailedResponse(UUID id,
                                  String description,
                                  Integer maxNumberOfGuests,
                                  boolean hasPrivateBathroom,
                                  boolean hasBathtub,
                                  boolean hasKitchen,
                                  boolean hasStove,
                                  boolean hasMicrowave,
                                  boolean arePetsAllowed,
                                  BigDecimal price) {
}
