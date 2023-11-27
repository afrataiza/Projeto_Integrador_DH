package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Policy;
import com.br.digital_hoteis.domain.exception.PolicyNotFoundException;
import com.br.digital_hoteis.domain.repository.PolicyRepository;
import com.br.digital_hoteis.domain.service.PolicyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;

@Slf4j
@Service
@AllArgsConstructor
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final ObjectMapper mapper;

    @Override
    public Page<Policy> findAllPolicies(Pageable pageable) {
        return policyRepository.findAll(pageable);
    }

    @Override
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public Policy findPolicyById(UUID id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException(id));
    }

    @Override
    public void updatePolicyById(UUID policyId, Map<String, Object> fields) {
        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException(policyId));
        Policy input = mapper.convertValue(fields, Policy.class);
        fields.forEach((property, value) -> {
            Field field = findField(Policy.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, policy, newValue);
        });
        policyRepository.save(policy);
    }

    @Override
    public void deletePolicyById(UUID policyId) {
        Policy hotel = policyRepository.findById(policyId)
                .orElseThrow(() -> new PolicyNotFoundException(policyId));
        policyRepository.delete(hotel);
    }
}
