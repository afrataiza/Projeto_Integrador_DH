package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.UserDetail;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class UserDetailSpecification {
    private UserDetailSpecification() {}

    public static Specification<UserDetail> byHotelId(UUID id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), id));
    }

}