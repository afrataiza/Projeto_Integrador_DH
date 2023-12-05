package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsImagesResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHotelRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateImageRequest;
import com.br.digital_hoteis.app.api.dto.response.ImagesDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.ImagesSummaryResponse;
import com.br.digital_hoteis.domain.entity.Images;
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

@Tag(name = "Images")
@RequestMapping("v1/images")
public interface ImagesApi {

    @ApiResponse(responseCode = "200", description = "Retrieves all images with pageable data",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsImagesResponse.class)))

    @GetMapping
    ResponseEntity<Page<ImagesSummaryResponse>> findAllImages(
            @Schema(implementation = PageParamsRequest.class) @PageableDefault Pageable page);


    @GetMapping("{imageId}")
    ResponseEntity<ImagesDetailedResponse> findImagesById(@PathVariable UUID imageId);

//    @PostMapping
//    ResponseEntity<Images> createImages(@RequestBody @Valid CreateImageRequest request);

    ResponseEntity<Images> createImages(@RequestBody @Valid CreateImageRequest request, CreateHotelRequest hotelRequest);

    @PatchMapping("{imageId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Images> updateImagesById(@PathVariable UUID imageId,
                                            @RequestBody Map<String, Object> fields);


    @DeleteMapping("{imageId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteImagesById(@PathVariable UUID imageId);
}
