package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsCharacteristicsResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateCharacteristicsRequest;
import com.br.digital_hoteis.app.api.dto.response.CharacteristicsDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.CharacteristicsSummaryResponse;
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

@Tag(name = "Characteristics")
@RequestMapping("v1/characteristics")
public interface CharacteristicsApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of characteristics",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsCharacteristicsResponse.class)))

    @GetMapping
    ResponseEntity<Page<CharacteristicsSummaryResponse>> findCharacteristics(@Schema(implementation = PageParamsRequest.class)
                                                                 @PageableDefault
                                                                 Pageable page);
    @GetMapping("{characteristicsId}")
    ResponseEntity<CharacteristicsDetailedResponse> findCharacteristicsById(@PathVariable UUID characteristicsId);

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> createCharacteristics(@RequestBody @Valid CreateCharacteristicsRequest request);

    @PatchMapping("{characteristicsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> updateCharacteristicsById(@PathVariable UUID characteristicsId,
                                            @RequestBody Map<String, Object> fields);

    @DeleteMapping("{characteristicsId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteCharacteristicsById(@PathVariable UUID characteristicsId);
}
