package com.br.digital_hoteis.app.api.dto.response;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record PolicyDetailedResponse(UUID id,
                                     Set<HotelSummaryResponse> hotels,
                                     LocalDate check_in_date,
                                     LocalDate check_out_date,
                                     String norms_description,
                                     String health_and_security_description,
                                     String cancellation_description,
                                     String cancellationPrepaymentConditions,
                                     String refundableDeposit,
                                     String childrenConditions,
                                     String cribsExtraBedsConditions,
                                     String ageRestriction,
                                     String petPolicy,
                                     String paymentPolicy) {
}
