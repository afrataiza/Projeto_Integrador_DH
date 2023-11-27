package com.br.digital_hoteis.app.api.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record RoomSummaryResponse(UUID id,
                                  String description,
                                  BigDecimal price) {
}
