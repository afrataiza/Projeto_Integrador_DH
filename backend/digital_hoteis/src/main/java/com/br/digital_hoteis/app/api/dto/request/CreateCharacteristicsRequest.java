package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateCharacteristicsRequest(
        @Schema(example = "Naming a characteristic") @NotBlank String name,
        @Schema(example = "https://unsplash.com/img.jpg") @NotBlank String icon
) {
}
