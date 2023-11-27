package com.br.digital_hoteis.app.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreatePolicyRequest(
        @NotNull @Schema(example = "2023-01-01") LocalDate checkInDate,
        @NotNull @Schema(example = "2023-01-10") LocalDate checkOutDate,
        @NotBlank @Schema(example = "Description of norms") String normsDescription,
        @NotBlank @Schema(example = "Description of health and security") String healthAndSecurityDescription,
        @NotBlank @Schema(example = "Description of cancellation") String cancellationDescription,
        @NotBlank @Schema(example = "Cancellation and prepayment conditions") String cancellationPrepaymentConditions,
        @NotBlank @Schema(example = "Refundable deposit conditions") String refundableDeposit,
        @NotBlank @Schema(example = "Conditions for children") String childrenConditions,
        @NotBlank @Schema(example = "Conditions for cribs and extra beds") String cribsExtraBedsConditions,
        @NotBlank @Schema(example = "Age restriction details") String ageRestriction,
        @NotBlank @Schema(example = "Pet policy details") String petPolicy,
        @NotBlank @Schema(example = "Payment policy details") String paymentPolicy
) {
}
