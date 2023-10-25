package br.com.digital_hoteis.app.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record PageParamsRequest(
        @Schema(example = "8")
        int page,
        @Schema(example = "1")
        int size,
        @Schema(example = "[\n\"name\"\n]")
        List<String> sort) {
}
