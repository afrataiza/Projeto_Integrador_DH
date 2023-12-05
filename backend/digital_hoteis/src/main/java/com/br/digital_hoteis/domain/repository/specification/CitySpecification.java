package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.City;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class CitySpecification {
    public static Specification<City> byCityId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city").get("id"), id));
    }
}
