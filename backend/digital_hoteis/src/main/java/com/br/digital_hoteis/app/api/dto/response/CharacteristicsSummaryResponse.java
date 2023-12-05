package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record CharacteristicsSummaryResponse(
        UUID id,
        String name
) {
}
