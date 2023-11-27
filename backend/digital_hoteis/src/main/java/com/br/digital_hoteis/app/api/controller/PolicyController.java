package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.PolicyApi;
import com.br.digital_hoteis.app.api.dto.request.CreatePolicyRequest;
import com.br.digital_hoteis.app.api.dto.response.HotelSummaryResponse;
import com.br.digital_hoteis.app.api.dto.response.PolicyDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.PolicySummaryResponse;
import com.br.digital_hoteis.domain.entity.Policy;
import com.br.digital_hoteis.domain.service.PolicyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class PolicyController implements PolicyApi {

    private final PolicyService policyService;
    @GetMapping
    public ResponseEntity<Page<PolicySummaryResponse>> findAllPolicies(Pageable page) {
        Page<Policy> pagePolicy = policyService.findAllPolicies(page);
        Page<PolicySummaryResponse> response = pagePolicy
                .map(policy -> new PolicySummaryResponse(
                        policy.getId(),
                        policy.getCheck_in_date(),
                        policy.getCheck_out_date(),
                        policy.getNorms_description(),
                        policy.getHealth_and_security_description(),
                        policy.getChildrenConditions(),
                        policy.getCribsExtraBedsConditions(),
                        policy.getAgeRestriction(),
                        policy.getPetPolicy(),
                        policy.getPaymentPolicy()));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<PolicyDetailedResponse> findPolicyById(@PathVariable UUID policyId) {
        Policy policy = policyService.findPolicyById(policyId);

        Set<HotelSummaryResponse> hotelSummaries = policy.getHotels()
                .stream()
                .map(hotel -> new HotelSummaryResponse(
                        hotel.getId(),
                        hotel.getTrading_name(),
                        hotel.getCnpj(),
                        hotel.getDescription()
                ))
                .collect(Collectors.toSet());

        PolicyDetailedResponse response = new PolicyDetailedResponse(
                policy.getId(),
                hotelSummaries,
                policy.getCheck_in_date(),
                policy.getCheck_out_date(),
                policy.getNorms_description(),
                policy.getHealth_and_security_description(),
                policy.getCancellation_description(),
                policy.getCancellationPrepaymentConditions(),
                policy.getRefundableDeposit(),
                policy.getChildrenConditions(),
                policy.getCribsExtraBedsConditions(),
                policy.getAgeRestriction(),
                policy.getPetPolicy(),
                policy.getPaymentPolicy()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createPolicy(@Valid @RequestBody CreatePolicyRequest request) {

        Policy policy = Policy.newPolicy(
                request.checkInDate(),
                request.checkOutDate(),
                request.normsDescription(),
                request.healthAndSecurityDescription(),
                request.cancellationDescription(),
                request.cancellationPrepaymentConditions(),
                request.refundableDeposit(),
                request.childrenConditions(),
                request.cribsExtraBedsConditions(),
                request.ageRestriction(),
                request.petPolicy(),
                request.paymentPolicy()
        );

        Policy createdPolicy = policyService.createPolicy(policy);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdPolicy.getId())
                        .toUri()
        ).build();
    }

    @Override
    public ResponseEntity<Void> updatePolicyById(UUID policyId, Map<String, Object> fields) {
        policyService.updatePolicyById(policyId, fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deletePolicyById(UUID policyId) {
        policyService.deletePolicyById(policyId);
        return ResponseEntity.noContent().build();
    }
}
