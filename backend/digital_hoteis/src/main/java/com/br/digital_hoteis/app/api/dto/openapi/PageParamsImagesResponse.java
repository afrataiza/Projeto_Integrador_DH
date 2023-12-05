package com.br.digital_hoteis.app.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.UUID;

@Getter
public class PageParamsImagesResponse {
    @Getter
    public static class ImagesResponse {
        @Schema(description = "The unique identifier for the images")
        private UUID id;

        @Schema(example = "Image title")
        private String title;

        @Schema(example = "https://example.com/image.jpg")
        private String url;

    }
}