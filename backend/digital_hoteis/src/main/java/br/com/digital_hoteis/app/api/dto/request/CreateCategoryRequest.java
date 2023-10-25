package br.com.digital_hoteis.app.api.dto.request;

import br.com.digital_hoteis.model.entity.RatingEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CreateCategoryRequest(
        @Schema(example = "[\"Hostel\", \"Apartment\"]") Set<RatingEnum> ratings,
        @Schema(example = "Category description") @NotBlank String description,
        @Schema(example = "https://unsplash.com/img.jpg") @NotBlank String image_url

        ) {
}
