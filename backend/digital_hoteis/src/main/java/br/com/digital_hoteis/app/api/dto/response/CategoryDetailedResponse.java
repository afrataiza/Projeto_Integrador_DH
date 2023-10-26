package br.com.digital_hoteis.app.api.dto.response;

import br.com.digital_hoteis.model.entity.RatingEnum;

import java.util.Set;
import java.util.UUID;

public record CategoryDetailedResponse(    UUID id,
                                           Set<RatingEnum> ratings,
                                           String description,
                                           String image_url) {
}
