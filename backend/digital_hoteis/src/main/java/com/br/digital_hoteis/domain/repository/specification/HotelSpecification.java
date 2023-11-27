package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.Hotel;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class HotelSpecification {
    public static Specification<Hotel> byCityId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city").get("id"), id));
    }

    public static Specification<Hotel> byHotelId(UUID hotel_id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), hotel_id));
    }
}
