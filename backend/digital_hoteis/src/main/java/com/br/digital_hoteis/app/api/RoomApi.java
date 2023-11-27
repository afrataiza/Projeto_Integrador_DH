package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRoomResponse;
import com.br.digital_hoteis.app.api.dto.request.CreateRoomRequest;
import com.br.digital_hoteis.app.api.dto.response.RoomDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.RoomSummaryResponse;
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

@Tag(name = "Rooms")
@RequestMapping("v1/rooms")
public interface RoomApi {

    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of rooms",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsRoomResponse.class)))

    @GetMapping
    ResponseEntity<Page<RoomSummaryResponse>> findAllRooms(@Schema(implementation = PageParamsRequest.class)
                                                                         @PageableDefault
                                                                         Pageable page);
    @GetMapping("{roomId}")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<RoomDetailedResponse> findRoomById(@PathVariable UUID roomId);

    @PostMapping
    ResponseEntity<Void> createRoom(@RequestBody @Valid CreateRoomRequest request);

    @PatchMapping("{roomId}")
    ResponseEntity<Void> updateRoom(@PathVariable UUID roomId,
                                           @RequestBody Map<String, Object> fields);

    @DeleteMapping("{roomId}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteRoomById(@PathVariable UUID roomId);

}

