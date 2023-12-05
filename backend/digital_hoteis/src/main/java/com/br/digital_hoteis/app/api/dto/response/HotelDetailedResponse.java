package com.br.digital_hoteis.app.api.dto.response;

import com.br.digital_hoteis.domain.entity.Policy;

import java.util.Set;
import java.util.UUID;

public record HotelDetailedResponse(UUID id,
                                    String trading_name,
                                    String cnpj,
                                    String description,
                                    CategorySummaryResponse category,
                                    ContactDetailedResponse contact,
                                    CityDetailedResponse city,
                                    Set<HostSummaryResponse> hosts,
                                    Policy policy) {
}
