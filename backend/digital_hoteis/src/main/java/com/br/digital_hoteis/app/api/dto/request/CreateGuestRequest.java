package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreateGuestRequest(
        @Schema(example = "John") @NotBlank String name,
        @Schema(example = "Doe") @NotBlank String surname,
        @Schema(example = "1990-01-01") LocalDate birthdate,
        @Schema(example = "M") @NotBlank String gender,
        @Schema(example = "Rio de janeiro") CreateCityRequest city,
        @Schema(example = "Phone contact, email or both") CreateContactRequest contact
) {
}
