package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record ImagesSummaryResponse(
        UUID id,
        String url,
        UUID hotelId
) {
}
