package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.HotelApi;
import com.br.digital_hoteis.app.api.dto.request.CreateContactRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHostRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHotelRequest;
import com.br.digital_hoteis.app.api.dto.request.CreatePolicyRequest;
import com.br.digital_hoteis.app.api.dto.response.*;
import com.br.digital_hoteis.domain.entity.*;
import com.br.digital_hoteis.domain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class HotelController implements HotelApi {

    private final HotelService hotelService;
    private final HostService hostService;
    private final ContactService contactService;
    private final CityService cityService;
    private final RoomService roomService;
    private final GuestService guestService;
    private final ImagesService imagesService;
    private final ReservationService reservationService;
    private final CharacteristicsService characteristicsService;
    private final ReviewScoreService reviewScoreService;

    @Override
    public ResponseEntity<Page<HotelSummaryResponse>> findAllHotels(Pageable page) {
        Page<Hotel> pageHotel = hotelService.findAllHotels(page);
        Page<HotelSummaryResponse> response = pageHotel
                .map(hotel -> new HotelSummaryResponse(
                        hotel.getId(),
                        hotel.getTrading_name(),
                        hotel.getCnpj(),
                        hotel.getDescription()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<HotelDetailedResponse> findHotelById(UUID hotel_id) {

        Hotel hotel = hotelService.findHotelById(hotel_id);

        Set<HostSummaryResponse> hosts = hotel.getHosts()
                .stream()
                .map(host -> new HostSummaryResponse(
                        host.getId(),
                        host.getName(),
                        host.getSurname()))
                .collect(Collectors.toSet());

        CategorySummaryResponse categories = new CategorySummaryResponse(
                hotel.getCategory().getId(),
                hotel.getCategory().getDescription()
        );

        HotelDetailedResponse response = new HotelDetailedResponse(
                hotel.getId(),
                hotel.getTrading_name(),
                hotel.getCnpj(),
                hotel.getDescription(),
                categories,
                new ContactDetailedResponse(
                        hotel.getContact().getId(),
                        hotel.getContact().getEmail(),
                        hotel.getContact().getPhone_number()),
                new CityDetailedResponse(
                        hotel.getCity().getId(),
                        hotel.getCity().getName(),
                        hotel.getCity().getStreet(),
                        hotel.getCity().getDistrict(),
                        hotel.getCity().getState(),
                        hotel.getCity().getZipcode(),
                        hotel.getCity().getCountry()),
                hosts,
                hotel.getPolicy()
                // adicionar imagem aqui???
        );
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<HostSummaryResponse>> findHostsByHotelId(UUID hotel_id, Pageable pageable) {
        Page<Host> hostsPage = hostService.findHostsByHotelId(hotel_id, pageable);
        Page<HostSummaryResponse> response = hostsPage.map( host ->
                new HostSummaryResponse(host.getId(), host.getName(), host.getSurname()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Page<GuestSummaryResponse>> findGuestByHotelId(UUID hotel_id, Pageable pageable) {
        Page<Guest> guestsPage = guestService.findGuestsByHotelId(hotel_id, pageable);
        Page<GuestSummaryResponse> response = guestsPage.map(guest ->
                new GuestSummaryResponse(
                        guest.getId(),
                        guest.getName(),
                        guest.getSurname()
                ));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Map<String, Object>> findHotelWithPolicy(@PathVariable UUID hotel_id) {
        Map<String, Object> hotelWithPolicyDetails = hotelService.getHotelWithPolicyDetails(hotel_id);
        return ResponseEntity.ok(hotelWithPolicyDetails);
    }


    @Override
    public ResponseEntity<Void> createHotel(CreateHotelRequest request) {
        Contact contact = new Contact();
        contact.setEmail(request.contact().email());
        contact.setPhone_number(request.contact().phone_number());

        City city = new City();
        city.setState(request.city().name());
        city.setState(request.city().street());
        city.setDistrict(request.city().district());
        city.setState(request.city().state());
        city.setZipcode(request.city().zipcode());
        city.setCountry(request.city().country());

        Category category = new Category();
        category.setDescription(request.category().description());
        category.setRatings(request.category().ratings());
        category.setImage_url(request.category().image_url());

        Set<Host> hosts = request.hosts().stream()
                .map(hostService::findHostById)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<Room> rooms = request.rooms().stream()
                .map(roomService::findRoomById)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<Characteristics> characteristics = request.characteristics().stream()
                .map(id -> characteristicsService.findCharacteristicsById(id.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Set<Images> images = request.images().stream()
                .map(id -> imagesService.findImagesById(id.getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());


        Set<ReviewScore> review_scores = new HashSet<>();

        Hotel hotel = Hotel.newHotel(
                request.trading_name(),
                review_scores,
                request.cnpj(),
                request.description(),
                category,
                contact,
                city,
                hosts,
                request.policy(),
                characteristics,
                images,
                rooms
        );

        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdHotel.getId())
                        .toUri()
        ).build();
    }


    private Set<Host> findHostById(Set<UUID> hostId) {
        return hostId.stream()
                .map(hostService::findHostById)
                .collect(Collectors.toSet());
    }

    private Set<Room> findRoomById(Set<UUID> roomId) {
        return roomId.stream()
                .map(roomService::findRoomById)
                .collect(Collectors.toSet());
    }


    @Override
    public ResponseEntity<Void> updateHotel(UUID hotel_id, Map<String, Object> fields) {
        hotelService.updateHotel(hotel_id, fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteHotelById(UUID hotel_id) {
        hotelService.deleteHotelById(hotel_id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> createHosts(UUID hotel_id, CreateContactRequest contactRequest, CreateHostRequest request) {
        Hotel hotel = hotelService.findHotelById(hotel_id);

        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        Contact contact = Contact.newContact(
                contactRequest.email(), contactRequest.phone_number()
        );

        Host host = Host.newHost(
                request.name(),
                request.surname(),
                request.birthdate(),
//                request.gender(),
                contact
        );
        hotel.getHosts().add(host);
        hotelService.updateHotel(hotel_id, Map.of("hosts", hotel.getHosts()));

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Override
    public ResponseEntity<Void> createHosts(UUID hotel_id, CreateHostRequest hostRequest, CreateContactRequest contactRequest) {

        Hotel hotel = hotelService.findHotelById(hotel_id);
        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }
        Contact contact = Contact.newContact(
                contactRequest.email(), contactRequest.phone_number()
        );
        Host host = Host.newHost(
                hostRequest.name(),
                hostRequest.surname(),
                hostRequest.birthdate(),
//                hostRequest.gender(),
                contact
        );
        hotel.getHosts().add(host);
        hotelService.updateHotel(hotel_id, Map.of("hosts", hotel.getHosts()));

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @Override
    public ResponseEntity<Void> addReviewScore(UUID hotel_id, UUID user_id, Integer review_scores) {
        Hotel hotel = hotelService.findHotelById(hotel_id);

        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }

        if (hotel.getReview_scores() == null) {
            hotel.setReview_scores(new HashSet<>());
        }

        hotelService.addReviewScore(hotel_id, user_id, review_scores);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addCharacteristics(UUID hotel_id, Characteristics characteristics) {
        Hotel hotel = hotelService.findHotelById(hotel_id);

        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }

        hotelService.addCharacteristics(hotel_id, characteristics);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> addPoliciesToAHotelById(UUID hotel_id, CreatePolicyRequest request) {

        Policy policy = Policy.newPolicy(
                request.checkInDate(),
                request.checkOutDate(),
                request.normsDescription(),
                request.healthAndSecurityDescription(),
                request.cancellationDescription(),
                request.cancellationPrepaymentConditions(),
                request.refundableDeposit(),
                request.childrenConditions(),
                request.cribsExtraBedsConditions(),
                request.ageRestriction(),
                request.petPolicy(),
                request.paymentPolicy()
        );

        hotelService.addPoliciesToAHotelById(hotel_id, policy);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Page<ReservationSummaryResponse>> findReservationsByHotelId(UUID hotel_id, Pageable pageable) {

        Page<Reservation> reservations = reservationService.findReservationsByHotelId(hotel_id, pageable);

        Page<ReservationSummaryResponse> response = reservations.map(reservation -> new ReservationSummaryResponse(
                reservation.getId(),
                reservation.getCreated_At(),
                reservation.getCheck_in_date(),
                reservation.getCheck_out_date(),
                new GuestSummaryResponse(
                        reservation.getGuest().getId(),
                        reservation.getGuest().getName(),
                        reservation.getGuest().getSurname()
                ),
                mapHostsToHostSummaryResponse(reservation.getHosts()),
                new HotelSummaryResponse(
                        reservation.getHotel().getId(),
                        reservation.getHotel().getTrading_name(),
                        reservation.getHotel().getCnpj(),
                        reservation.getHotel().getDescription()
                )
        ));

        return ResponseEntity.ok(response);
    }

    private Set<HostSummaryResponse> mapHostsToHostSummaryResponse(Set<Host> hosts) {
        return hosts.stream()
                .map(host -> new HostSummaryResponse(host.getId(), host.getName(), host.getSurname()))
                .collect(Collectors.toSet());
    }


}
