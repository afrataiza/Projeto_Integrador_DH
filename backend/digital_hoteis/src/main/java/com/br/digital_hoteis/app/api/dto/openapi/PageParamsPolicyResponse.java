package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.Hotel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
public class PageParamsPolicyResponse {
    @Schema(description = "The unique identifier for the policy")
    private UUID id;

    @Schema(description = "A set of hotels associated with the policy")
    private Set<Hotel> hotels;

    @Schema(description = "The check-in date for the policy")
    private LocalDate checkInDate;

    @Schema(description = "The check-out date for the policy")
    private LocalDate checkOutDate;

    @Schema(description = "Description of norms for the policy")
    private String normsDescription;

    @Schema(description = "Description of health and security for the policy")
    private String healthAndSecurityDescription;

    @Schema(description = "Description of cancellation for the policy")
    private String cancellationDescription;

    @Schema(description = "Cancellation and prepayment conditions for the policy")
    private String cancellationPrepaymentConditions;

    @Schema(description = "Refundable deposit conditions for the policy")
    private String refundableDeposit;

    @Schema(description = "Conditions for children for the policy")
    private String childrenConditions;

    @Schema(description = "Conditions for cribs and extra beds for the policy")
    private String cribsExtraBedsConditions;

    @Schema(description = "Age restriction details for the policy")
    private String ageRestriction;

    @Schema(description = "Pet policy details for the policy")
    private String petPolicy;

    @Schema(description = "Payment policy details for the policy")
    private String paymentPolicy;
}
