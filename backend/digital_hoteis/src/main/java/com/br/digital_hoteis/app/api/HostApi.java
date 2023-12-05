package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsHostResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateContactRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHostRequest;
import com.br.digital_hoteis.app.api.dto.response.HostDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.HostSummaryResponse;
import com.br.digital_hoteis.domain.entity.UserPermissionEnum;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;


@Tag(name = "Hosts")
@RequestMapping("v1/hosts")
public interface HostApi {
    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of hosts",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = PageParamsHostResponse.class)))

    @GetMapping
        ResponseEntity<Page<HostSummaryResponse>> findAllHosts(
                @Schema(implementation = PageParamsRequest.class)
                @PageableDefault Pageable page);

    @GetMapping("{hostId}")
    @PreAuthorize("hasAuthority('USER')")
    ResponseEntity<HostDetailedResponse> findHostById(@PathVariable UUID hostId);

    @PostMapping
    ResponseEntity<Void> createHost(CreateHostRequest request, CreateContactRequest contactRequest);

    @PatchMapping("{hostId}")
    ResponseEntity<Void> updateHostById(@PathVariable UUID hostId,
                                        @RequestBody Map<String, Object> fields);

    @DeleteMapping("{hostId}")
//  @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deleteHostById(@PathVariable UUID hostId);

    @PostMapping("{hostId}/manageUserRoles")
    @ApiResponse(responseCode = "200", description = "Método para adicionar novas funções.")
    ResponseEntity<Void> manageUserRoles(@PathVariable UUID hostId,
                                         @RequestParam("userId") UUID userId,
                                         @RequestParam("newRole") UserPermissionEnum newRole);
}


