package br.com.digital_hoteis.app.api.dto.response;

import br.com.digital_hoteis.model.entity.RatingEnum;

import java.util.Set;
import java.util.UUID;

public record CategorySummaryResponse(UUID id,
                                      String description) {
}
