package com.br.digital_hoteis.app.api.dto.response;

import java.util.UUID;

public record CityDetailedResponse(
        UUID id,
        String name,
        String street,
        String district,
        String state,
        String zipcode,
        String country
) {
}
