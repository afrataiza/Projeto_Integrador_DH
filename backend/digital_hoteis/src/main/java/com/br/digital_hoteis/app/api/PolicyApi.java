package com.br.digital_hoteis.app.api;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsPolicyResponse;
import com.br.digital_hoteis.app.api.dto.openapi.PageParamsRequest;
import com.br.digital_hoteis.app.api.dto.request.CreatePolicyRequest;
import com.br.digital_hoteis.app.api.dto.response.PolicyDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.PolicySummaryResponse;
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

@Tag(name = "Policies")
@RequestMapping("v1/policies")
public interface PolicyApi {
    @ApiResponse(responseCode = "200", description = "Searching for all the pageable data of categories",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PageParamsPolicyResponse.class)))

    @GetMapping
    ResponseEntity<Page<PolicySummaryResponse>> findAllPolicies(@Schema(implementation = PageParamsRequest.class)
                                                            @PageableDefault
                                                            Pageable page);
    @GetMapping("{policyId}")
    ResponseEntity<PolicyDetailedResponse> findPolicyById(@PathVariable UUID policyId);


//    @GetMapping("{policyId}/policies")
//    @PreAuthorize("hasAuthority('USER')")
//    ResponseEntity<Page<HotelSummaryResponse>> findHotelByPolicyId(@PathVariable UUID policyId,
//                                                                 @PageableDefault Pageable pageable);

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> createPolicy(@RequestBody @Valid CreatePolicyRequest request);

    @PatchMapping("{policyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> updatePolicyById(@PathVariable UUID policyId,
                                        @RequestBody Map<String, Object> fields);

    @DeleteMapping("{policyId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Void> deletePolicyById(@PathVariable UUID policyId);
}
