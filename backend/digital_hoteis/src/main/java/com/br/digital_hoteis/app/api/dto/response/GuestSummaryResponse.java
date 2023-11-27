package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record GuestSummaryResponse(UUID id,
                                   String name,
                                   String surname) {
}
