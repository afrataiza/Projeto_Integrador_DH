package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.exception.GuestNotFoundException;
import com.br.digital_hoteis.domain.repository.GuestRepository;
import com.br.digital_hoteis.domain.repository.specification.GuestSpecification;
import com.br.digital_hoteis.domain.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;

@Slf4j
@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final ObjectMapper mapper;
    @Override
    public Guest findGuestById(UUID id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException(id));
    }

    @Override
    public Page<Guest> findAllGuests(Pageable page) {
        return guestRepository.findAll(page);
    }

    @Override
    public Guest createGuest(Guest guest) {
        if (StringUtils.isBlank(guest.getContact().getEmail())
                && StringUtils.isBlank(guest.getContact().getPhone_number())) {
            throw new ValidationException("At least one field must be filled.");
        }
        return guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(UUID id, Map<String, Object> fields) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException(id));
        Guest input = mapper.convertValue(fields, Guest.class);
        fields.forEach((property, value) -> {
            Field field = findField(Guest.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, guest, newValue);
        });
        return guestRepository.save(guest);
    }

    @Override
    public void deleteGuestById(UUID id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new GuestNotFoundException(id));
        guestRepository.delete(guest);
    }

    @Override
    public Page<Guest> findGuestsByHotelId(UUID id, Pageable page) {
        return guestRepository.findAll(GuestSpecification.byHotelId(id), page);
    }

}
