package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CreateImageRequest(
        @Schema(example = "Image Title") @NotBlank String title,
        @Schema(example = "https://example.com/image.jpg") @NotBlank String url,
        @Schema(example = "hotel-id") @NotBlank UUID hotelId
) {
}

