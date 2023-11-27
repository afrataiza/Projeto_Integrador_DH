package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CategorySpecification {
    private CategorySpecification() {}

    public static Specification<Hotel> byHotelId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), id));
    }

}