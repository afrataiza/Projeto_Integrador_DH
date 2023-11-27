package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record ContactDetailedResponse(
        UUID id,
        String email,
        String phone_number
) {
}
