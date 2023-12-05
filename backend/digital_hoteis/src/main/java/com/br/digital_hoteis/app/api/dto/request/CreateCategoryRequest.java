package com.br.digital_hoteis.app.api.dto.request;

import com.br.digital_hoteis.domain.entity.RatingEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @Schema(example = "[\"BAD\"], [\"AVERAGE\"], [\"GOOD\"],[\"VERY_GOOD\"], [\"EXCELLENT\"]") RatingEnum ratings,
        @Schema(example = "Category description") @NotBlank String description,
        @Schema(example = "https://unsplash.com/img.jpg") @NotBlank String image_url

) {
}
