package com.br.digital_hoteis.app.api.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record HostDetailedResponse(UUID id,
                                   String name,
                                   String surname,
                                   LocalDate birthdate,
                                   String gender,
                                   ContactSummaryResponse contact,
                                   HotelSummaryResponse hotel) {
}
