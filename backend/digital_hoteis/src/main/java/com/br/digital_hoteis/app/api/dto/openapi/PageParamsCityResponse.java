package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.RatingEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PageParamsCityResponse {

    @Getter
    public static class Category {
        @Schema(description = "The unique identifier for the category")
        private UUID id;

        @Schema(description = "A set of rating values for the category")
        private RatingEnum ratings;

        @Schema(description = "The URL of the category's image", example = "http://unsplash.com/imagemaqui.jpg")
        private String image_url;
    }
}