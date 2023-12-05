package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.Host;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class HostSpecification {

    private HostSpecification() {}

    public static Specification<Host> byHotelId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), id));
    }
}
