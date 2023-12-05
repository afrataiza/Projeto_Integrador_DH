package com.br.digital_hoteis.app.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class PageParamsContactResponse {
    @Getter
    public static class ContactResponse {
        @Schema(description = "The unique identifier for the contact information")
        private UUID id;

        @Schema(example = "contact@example.com")
        private String email;

        @Schema(example = "(12) 34567-8901")
        private String phone_number;

        @Schema(example = "2023-09-27T15:30:00Z")
        private Instant created_At;

        @Schema(example = "2023-09-27T16:45:00Z")
        private Instant updated_At;


    }
}
