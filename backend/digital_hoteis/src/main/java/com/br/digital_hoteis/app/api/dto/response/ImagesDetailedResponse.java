package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record ImagesDetailedResponse(
        UUID id,
        String title,
        String url,
        UUID hotelId
) {
}
