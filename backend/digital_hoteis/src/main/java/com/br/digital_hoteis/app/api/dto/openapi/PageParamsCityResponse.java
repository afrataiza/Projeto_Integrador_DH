package com.br.digital_hoteis.app.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class PageParamsCityResponse {
    @Getter
    public static class CityResponse {
        @Schema(description = "The unique identifier for the cities")
        private UUID id;

        @Schema(example = "City's name")
        private String name;

        @Schema(example = "City's street")
        private String street;

        @Schema(example = "City's district")
        private String district;

        @Schema(example = "City's state")
        private String state;

        @Schema(example = "12345")
        private String zipcode;

        @Schema(example = "City's country")
        private String country;

        @Schema(example = "2023-09-27T15:30:00Z")
        private Instant created_At;

        @Schema(example = "2023-09-27T16:45:00Z")
        private Instant updated_At;


    }
}