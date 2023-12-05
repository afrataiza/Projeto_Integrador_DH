package com.br.digital_hoteis.domain.repository.specification;

import com.br.digital_hoteis.domain.entity.Reservation;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public final class ReservationSpecification {

    private ReservationSpecification() {}

    public static Specification<Reservation> byHotelId(UUID hotelId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("hotel").get("id"), hotelId));
    }

    public static Specification<Reservation> byGuestId(UUID guestId) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("guest").get("id"), guestId));
    }

}
