package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.ReservationApi;
import com.br.digital_hoteis.app.api.dto.request.CreateReservationRequest;
import com.br.digital_hoteis.app.api.dto.response.*;
import com.br.digital_hoteis.domain.entity.*;
import com.br.digital_hoteis.domain.service.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ReservationController implements ReservationApi {

    private final ReservationService reservationService;
    private final GuestService guestService;
    private final HostService hostService;
    private final HotelService hotelService;
    private final RoomService roomService;


    @Override
    public ResponseEntity<Page<ReservationSummaryResponse>> findAllReservations(Pageable page) {
        Page<Reservation> pageReservation = reservationService.findAllReservations(page);
        Page<ReservationSummaryResponse> response = pageReservation
                .map(reservation -> {
                    Set<HostSummaryResponse> hostSummaries = reservation.getHosts()
                            .stream()
                            .map(host -> new HostSummaryResponse(
                                    host.getId(),
                                    host.getName(),
                                    host.getSurname()))
                            .collect(Collectors.toSet());

                    return new ReservationSummaryResponse(
                            reservation.getId(),
                            reservation.getCreated_At(),
                            reservation.getCheck_in_date(),
                            reservation.getCheck_in_date(),
                            new GuestSummaryResponse(
                                    reservation.getGuest().getId(),
                                    reservation.getGuest().getName(),
                                    reservation.getGuest().getSurname()),
                            hostSummaries,
                            new HotelSummaryResponse(
                                    reservation.getHotel().getId(),
                                    reservation.getHotel().getTrading_name(),
                                    reservation.getHotel().getDescription(),
                                    reservation.getHotel().getCnpj()));
                });
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<ReservationDetailedResponse> findReservationById(UUID reservationId) {
        Reservation reservation = reservationService.findReservationById(reservationId);

        GuestSummaryResponse guestSummary = new GuestSummaryResponse(
                reservation.getGuest().getId(),
                reservation.getGuest().getName(),
                reservation.getGuest().getSurname()
        );

        Set<HostSummaryResponse> hostSummary = reservation.getHosts()
                .stream()
                .map( host -> new HostSummaryResponse(
               host.getId(),
               host.getName(),
               host.getSurname())).collect(Collectors.toSet());

        HotelSummaryResponse hotelSummary = new HotelSummaryResponse(
                reservation.getHotel().getId(),
                reservation.getHotel().getTrading_name(),
                reservation.getHotel().getCnpj(),
                reservation.getHotel().getDescription()
        );

        ReservationSummaryResponse reservationSummary = new ReservationSummaryResponse(
                reservation.getId(),
                reservation.getCreated_At(),
                reservation.getCheck_in_date(),
                reservation.getCheck_out_date(),
                guestSummary,
                hostSummary,
                hotelSummary
        );

        Set<RoomSummaryResponse> roomSummaryResponses = reservation.getRooms()
                .stream()
                .map(room -> new RoomSummaryResponse(room.getId(), room.getDescription(), room.getPrice()))
                .collect(Collectors.toSet());

        ReservationDetailedResponse response = new ReservationDetailedResponse(
                reservation.getId(),
                roomSummaryResponses,
                reservation.getCreated_At(),
                reservation.getCheck_in_date(),
                reservation.getCheck_out_date(),
                reservation.getRequests(),
                guestSummary,
                hostSummary,
                hotelSummary
        );

        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<Void> createReservation(@RequestBody @Valid CreateReservationRequest request) {

        Guest guest = guestService.findGuestById(request.guest_id());
        Hotel hotel = hotelService.findHotelById(request.hotel_id());

        Set<Room> rooms = request.rooms()
                .stream()
                .map(roomService::findRoomById)
                .collect(Collectors.toSet());

        Set<Host> hosts = request.hosts()
                .stream()
                .map(hostService::findHostById)
                .collect(Collectors.toSet());


        Reservation reservation = Reservation.newReservation(
                rooms,
                hotel,
//                request.created_At(),
                request.check_in_date(),
                request.check_out_date(),
                request.requests(),
                guest,
                hosts
        );

        Reservation createdReservation = reservationService.createReservation(reservation);

        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdReservation.getId()).toUri()
        ).build();
    }



    @Override
    public ResponseEntity<Void> updateReservation(UUID reservationId, Map<String, Object> fields) {
        reservationService.updateReservation(reservationId, fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteReservationById(UUID reservationId) {
        reservationService.deleteReservationById(reservationId);
        return ResponseEntity.noContent().build();
    }
}
