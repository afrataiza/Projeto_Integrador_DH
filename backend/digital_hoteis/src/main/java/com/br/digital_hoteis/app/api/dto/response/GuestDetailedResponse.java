package com.br.digital_hoteis.app.api.dto.response;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record GuestDetailedResponse(UUID id,
                                    String name,
                                    String surname,
                                    LocalDate birthdate,
                                    String gender,
                                    CitySummaryResponse city,
                                    ContactSummaryResponse contact,
                                    Set<ReservationSummaryResponse> reservation) {
}
