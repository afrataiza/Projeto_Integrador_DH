package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record CreateReservationRequest(
        @Schema(description = "The rooms included in the reservation") Set<UUID> rooms,
//        @Schema(example = "22:10:10", description = "Registration of the reservation's creation time") @NotNull Instant created_At,
        @Schema(example = "2023-01-01", description = "Check-in date") @NotNull LocalDate check_in_date,
        @Schema(example = "2023-01-07", description = "Check-out date") @NotNull LocalDate check_out_date,
        @Schema(description = "Special requests for the reservation") @NotBlank String requests,
        @Schema(example = "c97a4c71-9a7c-4ca2-98e5-20b0f5c17d15") UUID guest_id,
        @Schema(example = "c97a4c71-9a7c-4ca2-98e5-20b0f5c17d45") UUID hotel_id,
        @Schema(example = "The host(s) responsible for the stay") Set<UUID> hosts) {
}
