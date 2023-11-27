package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record CharacteristicsDetailedResponse(UUID id,
                                              String name,
                                              String icon) {
}
