package br.com.digital_hoteis.app.api.dto.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PageParamsResponse<T> {
    private List<T> content;
    private Integer size;
    @Schema(example = "20", description = "total quantity of the elements")
    private Integer totalElements;
    @Schema(example = "3", nullable = true)
    private Integer totalPage;
    private Integer number;

    @Data
    public static class CategoryResponse {
        private UUID id;
        @Schema(example = "Hostel")
        private String name;
    }
}
