package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.CityApi;
import com.br.digital_hoteis.app.api.dto.request.CreateCityRequest;
import com.br.digital_hoteis.app.api.dto.response.*;
import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Reservation;
import com.br.digital_hoteis.domain.service.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CityController implements CityApi {

    private final HotelService hotelService;
    private final CityService cityService;
    private final GuestService guestService;
    private final ReservationService reservationService;
    private final HostService hostService;


    @Override
    public ResponseEntity<Page<CitySummaryResponse>> findAllCities(Pageable page) {
        Page<City> pageCity = cityService.findAllCities(page);
        Page<CitySummaryResponse> response = pageCity
                .map(city -> new CitySummaryResponse(city.getId(), city.getName(), city.getCountry()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CityDetailedResponse> findCityById(UUID cityId) {
       City city = cityService.findCityById(cityId);

       CityDetailedResponse response = new CityDetailedResponse(
               city.getId(),
               city.getName(),
               city.getStreet(),
               city.getDistrict(),
               city.getState(),
               city.getZipcode(),
               city.getCountry()
       );

       return ResponseEntity.ok(response);
    }

    // método que nos permita filtrar os produtos por cidade e duas datas.
    @Override
    public ResponseEntity<Page<ReservationSummaryResponse>> findAvailableHotelsInCityByDates(
            UUID cityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate check_in_date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate check_out_date,
            Pageable pageable) {

        City city = cityService.findCityById(cityId);
        Page<Hotel> availableHotels = cityService.findAvailableHotelsInCityByDates(cityId, check_in_date, check_out_date, pageable);

        List<ReservationSummaryResponse> responseList = new ArrayList<>();

        availableHotels.forEach(hotel -> {
            Page<Guest> guests = guestService.findGuestsByHotelId(hotel.getId(), pageable);

            guests.forEach(guest -> {

                Reservation reservation = reservationService.findReservationByGuestAndDates(guest, check_in_date, check_out_date);

                if (reservation != null) {
                    GuestSummaryResponse guestSummary = new GuestSummaryResponse(guest.getId(), guest.getName(), guest.getSurname());

                    Set<HostSummaryResponse> hostSummaries = reservation.getHosts().stream()
                            .map(host -> new HostSummaryResponse(host.getId(), host.getName(), host.getSurname()))
                            .collect(Collectors.toSet());

                    HotelSummaryResponse hotelSummary = new HotelSummaryResponse(hotel.getId(), hotel.getTrading_name(), hotel.getCnpj(), hotel.getDescription());

                    ReservationSummaryResponse reservationResponse = new ReservationSummaryResponse(
                            reservation.getId(),
                            reservation.getCreated_At(),
                            check_in_date,
                            check_out_date,
                            guestSummary,
                            hostSummaries,
                            hotelSummary
                    );

                    responseList.add(reservationResponse);
                }
            });
        });

        Page<ReservationSummaryResponse> response = new PageImpl<>(responseList, pageable, responseList.size());

        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<Page<HotelSummaryResponse>> findHotelByCityId(UUID cityId, Pageable pageable) {
        Page<Hotel> hotelsPage = hotelService.findHotelByCityId(cityId, pageable);
        Page<HotelSummaryResponse> response = hotelsPage.map( hotel ->
                new HotelSummaryResponse(
                        hotel.getId(),
                        hotel.getTrading_name(),
                        hotel.getCnpj(),
                        hotel.getDescription()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> createCity(CreateCityRequest request) {

        City city = City.newCity(
                request.name(),
                request.street(),
                request.district(),
                request.state(),
                request.zipcode(),
                request.country()
        );

        City createdCity = cityService.createCity(city);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdCity.getId()).toUri()).build();

    }

    @Override
    public ResponseEntity<Void> updateCityById(UUID cityId, Map<String, Object> fields) {
        cityService.updateCity(cityId,fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteCityById(UUID cityId) {
        cityService.deleteCityById(cityId);
        return ResponseEntity.noContent().build();
    }
}
