package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateRoomRequest(
        @Schema(example = "c97a4c71-9a7c-4ca2-98e5-20b0f5c17d14") UUID hotel_id,
        @Schema(description = "The description of the room") @NotBlank String description,
        @Schema(description = "Maximum number of guests allowed in the room") Integer maxNumberOfGuests,
        @Schema(description = "Indicates if the room has a private bathroom") boolean hasPrivateBathroom,
        @Schema(description = "Indicates if the room has a bathtub") boolean hasBathtub,
        @Schema(description = "Indicates if the room has a kitchen") boolean hasKitchen,
        @Schema(description = "Indicates if the room has a stove") boolean hasStove,
        @Schema(description = "Indicates if the room has a microwave") boolean hasMicrowave,
        @Schema(description = "Indicates if pets are allowed in the room") boolean arePetsAllowed,
        @Schema(description = "The price of the room") BigDecimal price
) {
}
