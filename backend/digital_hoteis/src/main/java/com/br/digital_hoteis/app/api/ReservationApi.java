package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsReservationResponse;
import com.br.digital_hoteis.app.api.dto.request.CreateReservationRequest;
import com.br.digital_hoteis.app.api.dto.response.ReservationDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.ReservationSummaryResponse;
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

@Tag(name = "Reservations")
@RequestMapping("v1/reservations")
public interface ReservationApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of reservations",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsReservationResponse.class)))

    @GetMapping
    ResponseEntity<Page<ReservationSummaryResponse>> findAllReservations(@Schema(implementation = PageParamsRequest.class)
                                                                  @PageableDefault
                                                                  Pageable page);
    @GetMapping("{reservationId}")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<ReservationDetailedResponse> findReservationById(@PathVariable UUID reservationId);

    @PostMapping
    ResponseEntity<Void> createReservation(@RequestBody @Valid CreateReservationRequest request);

    @PatchMapping("{reservationId}")
    ResponseEntity<Void> updateReservation(@PathVariable UUID reservationId,
                                                @RequestBody Map<String, Object> fields);

    @DeleteMapping("{reservationId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteReservationById(@PathVariable UUID reservationId);

}
