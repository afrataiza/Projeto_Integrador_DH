package com.br.digital_hoteis.app.api.dto.response;

import com.br.digital_hoteis.domain.entity.RatingEnum;

import java.util.UUID;

public record CategoryDetailedResponse(UUID id,
                                       RatingEnum ratings,
                                       String description,
                                       String image_url) {
}
