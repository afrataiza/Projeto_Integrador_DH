package br.com.digital_hoteis.app.api;

import br.com.digital_hoteis.app.api.dto.openapi.PageParamsCategoryResponse;
import br.com.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import br.com.digital_hoteis.app.api.dto.request.CreateCategoryRequest;
import br.com.digital_hoteis.app.api.dto.response.CategoryDetailedResponse;
import br.com.digital_hoteis.app.api.dto.response.CategorySummaryResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Tag(name = "Categories")
@RequestMapping("v1/categories")
public interface CategoryApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pages",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsCategoryResponse.class)))

    @GetMapping
    ResponseEntity<Page<CategorySummaryResponse>> findCategories(@Schema(implementation = PageParamsRequest.class)
                                                                  @PageableDefault
                                                                  Pageable page);
    @GetMapping("{categoryId}")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<CategoryDetailedResponse> findCategoriesById(@PathVariable UUID categoryId);

    @PostMapping
    ResponseEntity<Void> createCategory(@RequestBody @Valid CreateCategoryRequest request);

    @PatchMapping("{categoryId}")
    ResponseEntity<Void> updateCategoryById(@PathVariable UUID categoryId,
                                                @RequestBody Map<String, Object> fields);

    @DeleteMapping("{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteCategoryById(@PathVariable UUID categoryId);
}
