package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface PolicyService {
    Page<Policy> findAllPolicies(Pageable pageable);
    Policy createPolicy(Policy policy);
    Policy findPolicyById(UUID id);
    void updatePolicyById(UUID policyId, Map<String, Object> fields);
    void deletePolicyById(UUID policyId);

}
