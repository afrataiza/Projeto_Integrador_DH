package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public interface ReservationService {
    Reservation findReservationById(UUID id);
    Page<Reservation> findAllReservations(Pageable page);
    Reservation createReservation(Reservation reservation);
    void updateReservation(UUID id, Map<String, Object> fields);
    void deleteReservationById(UUID id);
    Reservation findReservationByGuestAndDates(Guest guest, LocalDate check_in_date, LocalDate check_out_date);
}
