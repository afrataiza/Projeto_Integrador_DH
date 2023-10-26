package br.com.digital_hoteis.app.api.dto.openapi;

import br.com.digital_hoteis.model.entity.RatingEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Set;
import java.util.UUID;

public class PageParamsCategoryResponse extends PageParamsResponse<PageParamsCategoryResponse.Category>{

    @Getter
    public static class Category {
        private UUID id;

        @Schema(example = "Hostels")
        Set<RatingEnum> ratings;

        @Schema(example = "http://unsplash.com/imagemaqui.jpg")
        private String image_url;

    }
}
