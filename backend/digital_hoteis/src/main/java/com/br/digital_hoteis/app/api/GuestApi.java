package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsGuestResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateGuestRequest;
import com.br.digital_hoteis.app.api.dto.response.GuestDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.GuestSummaryResponse;
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

@Tag(name = "Guests")
@RequestMapping("v1/guests")
public interface GuestApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of guests",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsGuestResponse.class)))

    @GetMapping
    ResponseEntity<Page<GuestSummaryResponse>> findGuests(@Schema(implementation = PageParamsRequest.class)
                                                                 @PageableDefault
                                                                 Pageable page);
    @GetMapping("{guestId}")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<GuestDetailedResponse> findGuestById(@PathVariable UUID guestId);


    @PostMapping
    ResponseEntity<Void> createGuest(@RequestBody @Valid CreateGuestRequest request);

    @PatchMapping("{guestId}")
    ResponseEntity<Void> updateGuestById(@PathVariable UUID guestId,
                                            @RequestBody Map<String, Object> fields);

    @DeleteMapping("{guestId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteGuestById(@PathVariable UUID guestId);
}