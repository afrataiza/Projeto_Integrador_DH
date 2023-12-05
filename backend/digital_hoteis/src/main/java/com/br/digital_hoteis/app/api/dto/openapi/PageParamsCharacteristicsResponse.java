package com.br.digital_hoteis.app.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;
@Getter
public class PageParamsCharacteristicsResponse {
    @Getter
    public static class City {
        @Schema(description = "The unique identifier for the characteristics")
        private UUID id;

        @Schema(description = "Name for the cities",example = "Bahia")
        private String name;

        @Schema(description = "Name for the countries", example = "Brazil")
        private String country;
    }
}
