package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsCityResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateCityRequest;
import com.br.digital_hoteis.app.api.dto.response.CityDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.CitySummaryResponse;
import com.br.digital_hoteis.app.api.dto.response.HotelSummaryResponse;
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

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Tag(name = "Cities")
@RequestMapping("v1/cities")
public interface CityApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of categories",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsCityResponse.class)))

    @GetMapping
    ResponseEntity<Page<CitySummaryResponse>> findAllCities(@Schema(implementation = PageParamsRequest.class)
                                                                 @PageableDefault
                                                                 Pageable page);
    @GetMapping("{cityId}")
    ResponseEntity<CityDetailedResponse> findCityById(@PathVariable UUID cityId);


    @GetMapping("{cityId}/available-hotels")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<Page<ReservationSummaryResponse>> findAvailableHotelsInCityByDates(
            @PathVariable UUID cityId,
            @RequestParam("check_in_date") LocalDate check_in_date,
            @RequestParam("check_out_date") LocalDate check_out_date,
            @PageableDefault Pageable pageable);

    @GetMapping("{cityId}/hotels")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<Page<HotelSummaryResponse>> findHotelByCityId(@PathVariable UUID cityId,
                                                                 @PageableDefault Pageable pageable);

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> createCity(@RequestBody @Valid CreateCityRequest request);

    @PatchMapping("{cityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> updateCityById(@PathVariable UUID cityId,
                                            @RequestBody Map<String, Object> fields);

    @DeleteMapping("{cityId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteCityById(@PathVariable UUID cityId);
}