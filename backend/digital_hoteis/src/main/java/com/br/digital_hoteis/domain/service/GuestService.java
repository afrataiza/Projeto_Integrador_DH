package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface GuestService {

    Guest findGuestById(UUID id);
    Page<Guest> findAllGuests(Pageable page);
    Guest createGuest(Guest guest);
    Guest updateGuest(UUID id, Map<String, Object> fields);
    void deleteGuestById(UUID id);
    Page<Guest> findGuestsByHotelId(UUID hotel_id, Pageable page);

}
