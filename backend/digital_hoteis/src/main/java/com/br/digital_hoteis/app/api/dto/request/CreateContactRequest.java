package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

public record CreateContactRequest(
        @Schema(example = "florzinha_sorridente@example.com",
                description = "Valid email address") @Email String email,
        @Schema(example = "(21) 98178-0991",
                description = "Valid telephone number in brazilian territory") String phone_number

) {
}
