package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsHotelResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateContactRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHostRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHotelRequest;
import com.br.digital_hoteis.app.api.dto.request.CreatePolicyRequest;
import com.br.digital_hoteis.app.api.dto.response.*;
import com.br.digital_hoteis.domain.entity.Characteristics;
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

@Tag(name = "Hotels")
@RequestMapping("v1/hotels")
public interface HotelApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of hotels",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsHotelResponse.class)))



    @GetMapping
    ResponseEntity<Page<HotelSummaryResponse>> findAllHotels(@Schema(implementation = PageParamsRequest.class)
                                                                @PageableDefault
                                                                Pageable page);

    @GetMapping("{hotelId}")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<HotelDetailedResponse> findHotelById(@PathVariable UUID hotel_id);

    @GetMapping("{hotelId}/hosts")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<HostSummaryResponse>> findHostsByHotelId(@PathVariable UUID hotel_id,
                                                                             @PageableDefault Pageable pageable);
    @GetMapping("{hotelId}/guests")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Page<GuestSummaryResponse>> findGuestByHotelId(@PathVariable UUID hotel_id,
                                                                  @PageableDefault Pageable pageable);


    @GetMapping("{hotelId}/policy")
    @PreAuthorize("hasAuthority('USER')")
    @ApiResponse(responseCode = "200", description = "Retrieve hotel with policy",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = HotelDetailedResponse.class)))
    ResponseEntity<Map<String, Object>> findHotelWithPolicy(@PathVariable UUID hotel_id);

    @PostMapping("{hotelId}/reviews")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<Void> addReviewScore(@PathVariable UUID hotel_id,
                                        @RequestParam UUID user_id,
                                        @RequestParam Integer review_scores);

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> createHotel(@RequestBody @Valid CreateHotelRequest request);

    @PatchMapping("{hotelId}")
    ResponseEntity<Void> updateHotel(@PathVariable UUID hotel_id,
                                               @RequestBody Map<String, Object> fields);

    @PostMapping("{hotelId}/policies")
    ResponseEntity<Void> addPoliciesToAHotelById(@PathVariable UUID hotelId, @RequestBody @Valid CreatePolicyRequest request);

    // busca as reservas pela id do hotel
    @GetMapping("{hotelId}/reservations")
    ResponseEntity<Page<ReservationSummaryResponse>> findReservationsByHotelId(@PathVariable UUID hotelId,
                                                                        @PageableDefault Pageable pageable);

    @DeleteMapping("{hotelId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteHotelById(@PathVariable UUID hotel_id);

    @PostMapping("{hotelId}/hosts")
    ResponseEntity<Void> createHosts(@PathVariable UUID hotel_id, @RequestBody CreateContactRequest contactRequest, @RequestBody CreateHostRequest request);

    @PostMapping("{hotelId}/characteristics")
    ResponseEntity<Void> addCharacteristics(@PathVariable UUID hotel_id, @RequestBody Characteristics characteristics);
    ResponseEntity<Void> createHosts(UUID hotel_id,
                                        @RequestBody @Valid CreateHostRequest hostRequest, @RequestBody @Valid CreateContactRequest contactRequest);


}



