package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.repository.CityRepository;
import com.br.digital_hoteis.domain.repository.ReservationRepository;
import com.br.digital_hoteis.domain.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
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
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final ReservationRepository reservationRepository;

    private final ObjectMapper mapper;

    @Override
    public City findCityById(UUID id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @Override
    public Page<City> findAllCities(Pageable page) {
        return cityRepository.findAll(page);
    }

    @Override
    public Page<Hotel> findAvailableHotelsInCityByDates(
            UUID cityId, LocalDate check_in_date,
            LocalDate check_out_date,
            Pageable page) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(cityId)));
        return cityRepository.findAvailableHotelsInCityByDates(city, check_in_date, check_out_date, page);
    }


    @Override
    public City createCity(City city) {
        try {
            return cityRepository.save(city);
        } catch (ConstraintViolationException e) {
            log.error("Validation error while creating the city: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateCity(UUID id, Map<String, Object> fields) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        City input = mapper.convertValue(fields, City.class);
        fields.forEach((property, value) -> {
            Field field = findField(City.class, property);
            if (field == null) {
                log.error("field not found in the payload! '%s', I'll ignore it.".formatted(property));
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, city, newValue);
        });
        cityRepository.save(city);
    }

    @Override
    public void deleteCityById(UUID id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        cityRepository.delete(city);
    }
}
