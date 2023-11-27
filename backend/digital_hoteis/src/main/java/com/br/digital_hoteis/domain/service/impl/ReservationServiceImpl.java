package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Reservation;
import com.br.digital_hoteis.domain.exception.HotelNotFoundException;
import com.br.digital_hoteis.domain.exception.ReservationNotFoundException;
import com.br.digital_hoteis.domain.repository.HotelRepository;
import com.br.digital_hoteis.domain.repository.ReservationRepository;
import com.br.digital_hoteis.domain.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;

@Slf4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final HotelRepository hotelRepository;
    private final ObjectMapper mapper;
    @Override
    public Reservation findReservationById(UUID id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public Page<Reservation> findAllReservations(Pageable page) {
        return reservationRepository.findAll(page);
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        UUID hotel_id = reservation.getHotel().getId();

        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new HotelNotFoundException(hotel_id));

        if (!hotel.isAvailable()) {
            throw new HotelNotFoundException(hotel_id);
        }

        return reservationRepository.save(reservation);
    }


    @Override
    public void updateReservation(UUID id, Map<String, Object> fields) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        Reservation input = mapper.convertValue(fields, Reservation.class);
        fields.forEach((property, value) -> {
            Field field = findField(Reservation.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, reservation, newValue);
        });
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(UUID id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
        reservationRepository.delete(reservation);
    }

    @Override
    public Reservation findReservationByGuestAndDates(Guest guest, LocalDate check_in_date, LocalDate check_out_date) {
        return reservationRepository.findByGuestAndDateRange(guest, check_in_date, check_out_date)
                .orElseThrow(() -> new ReservationNotFoundException(guest.getId()));
    }
}
