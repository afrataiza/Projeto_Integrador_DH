package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record HotelSummaryResponse(UUID id,
                                   String trading_name,
                                   String cnpj,
                                   String description) {
}
