package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCityRequest(
        @Schema(example = "Caxias") String name,
        @Schema(example = "123 Rua das Flores") String street,
        @Schema(example = "Centro") String district,
        @Schema(example = "Rio de Janeiro") String state,
        @Schema(example = "12345-678") String zipcode,
        @Schema(example = "Brazil") String country) {
}
