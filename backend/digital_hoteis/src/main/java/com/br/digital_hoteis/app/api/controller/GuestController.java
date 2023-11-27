package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.GuestApi;
import com.br.digital_hoteis.app.api.dto.request.CreateGuestRequest;
import com.br.digital_hoteis.app.api.dto.response.*;
import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Contact;
import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.br.digital_hoteis.domain.entity.City.newCity;
import static com.br.digital_hoteis.domain.entity.Contact.newContact;

@RestController
@AllArgsConstructor
public class GuestController implements GuestApi {

    private final GuestService guestService;

    @Override
    public ResponseEntity<Page<GuestSummaryResponse>> findGuests(Pageable page) {
        Page<Guest> pageGuest = guestService.findAllGuests(page);
        Page<GuestSummaryResponse> response = pageGuest
                .map(guest -> new GuestSummaryResponse(guest.getId(), guest.getName(), guest.getSurname()));
        return ResponseEntity.ok(response);
    }
    @Override
    public ResponseEntity<GuestDetailedResponse> findGuestById(UUID guestId) {
        Guest guest = guestService.findGuestById(guestId);



        Set<ReservationSummaryResponse> reservations = guest.getReservations()
                .stream()
                .map(reservation -> new ReservationSummaryResponse(
                        reservation.getId(),
                        reservation.getCheck_in_date(),
                        reservation.getCheck_out_date(),
                        new GuestSummaryResponse(
                                reservation.getGuest().getId(),
                                reservation.getGuest().getName(),
                                reservation.getGuest().getSurname()),
                        reservation.getHosts().stream()
                                .map(host -> new HostSummaryResponse(
                                        host.getId(),
                                        host.getName(),
                                        host.getSurname()))
                                .collect(Collectors.toSet()), // Collect hosts into a set
                        new HotelSummaryResponse(
                                reservation.getHotel().getId(),
                                reservation.getHotel().getTrading_name(),
                                reservation.getHotel().getCnpj(),
                                reservation.getHotel().getDescription())
                ))
                .collect(Collectors.toSet());


        GuestDetailedResponse response = new GuestDetailedResponse(
                guest.getId(),
                guest.getName(),
                guest.getSurname(),
                guest.getBirthdate(),
                guest.getGender(),
                new CitySummaryResponse(
                        guest.getCity().getId(),
                        guest.getCity().getName(),
                        guest.getCity().getCountry()),
                new ContactSummaryResponse(
                        guest.getContact().getId(),
                        guest.getContact().getEmail(),
                        guest.getContact().getPhone_number()),
                reservations
        );

        return ResponseEntity.ok(response);
    }



    @Override
    public ResponseEntity<Void> createGuest(CreateGuestRequest request) {

        Guest guest = new Guest();

        City city = newCity(
                request.city().name(),
                request.city().street(),
                request.city().district(),
                request.city().state(),
                request.city().zipcode(),
                request.city().country());

        Contact contact = newContact(
                request.contact().email(),
                request.contact().phone_number()
        );

       guest.setName(request.name());
       guest.setBirthdate(request.birthdate());
       guest.setCity(city);
       guest.setGender(request.gender());
       guest.setContact(contact);

        Guest createdGuest = guestService.createGuest(guest);
        return ResponseEntity.created(
                        ServletUriComponentsBuilder.fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(createdGuest.getId())
                                .toUri())
                .build();
    }

    public ResponseEntity<Void> updateGuestById(UUID guestId, Map<String, Object> fields) {
        guestService.updateGuest(guestId, fields);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteGuestById(UUID guestId) {
        guestService.deleteGuestById(guestId);
        return ResponseEntity.noContent().build();
    }
}
