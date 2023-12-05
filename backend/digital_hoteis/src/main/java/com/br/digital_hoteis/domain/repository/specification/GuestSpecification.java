package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.Guest;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class GuestSpecification {

    private GuestSpecification() {}

    public static Specification<Guest> byHotelId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), id));
    }
}
