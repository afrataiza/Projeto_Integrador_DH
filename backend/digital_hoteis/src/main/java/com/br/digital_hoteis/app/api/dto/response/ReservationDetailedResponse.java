package com.br.digital_hoteis.app.api.dto.response;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record ReservationDetailedResponse(
        UUID id,
        Set<RoomSummaryResponse> rooms,
        LocalDate check_in_date,
        LocalDate check_out_date,
        String requests,
        GuestSummaryResponse guest,
        Set<HostSummaryResponse> hosts,
        HotelSummaryResponse hotel
) {
}
