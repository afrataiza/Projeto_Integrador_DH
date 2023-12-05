package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public interface CityService {
    City findCityById(UUID id);

    Page<City> findAllCities(Pageable page);

    Page<Hotel> findAvailableHotelsInCityByDates(
            UUID cityId,
            LocalDate check_in_date,
            LocalDate check_out_date,
            Pageable page);

    City createCity(City city);

    void updateCity(UUID id, Map<String, Object> fields);

    void deleteCityById(UUID id);
}
