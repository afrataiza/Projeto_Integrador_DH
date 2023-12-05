package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Characteristics;
import com.br.digital_hoteis.domain.exception.CharacteristicsNotFoundException;
import com.br.digital_hoteis.domain.repository.CharacteristicsRepository;
import com.br.digital_hoteis.domain.service.CharacteristicsService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CharacteristicsServiceImpl implements CharacteristicsService {

    private final CharacteristicsRepository characteristicsRepository;
    private final ObjectMapper mapper;


    @Override
    public Page<Characteristics> findAllCharacteristics(Pageable pageable) {
        return characteristicsRepository.findAll(pageable);
    }


    @Override
    public Characteristics createCharacteristics(Characteristics characteristics) {
        return characteristicsRepository.save(characteristics);
    }

    @Override
    public Characteristics findCharacteristicsById(UUID id) {
        return characteristicsRepository.findById(id)
                .orElseThrow(() -> new CharacteristicsNotFoundException(id));
    }

    @Override
    public Characteristics updateCharacteristicsById(UUID id, Map<String, Object> fields) {
        Characteristics characteristics = characteristicsRepository.findById(id)
                .orElseThrow(() -> new CharacteristicsNotFoundException(id));
        Characteristics input = mapper.convertValue(fields, Characteristics.class);
        fields.forEach((property, value) -> {
            Field field = findField(Characteristics.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, characteristics, newValue);
        });
        return characteristicsRepository.save(characteristics);
    }


    @Override
    public void deleteCharacteristicsById(UUID id) {
        Characteristics characteristics = characteristicsRepository
                .findById(id)
                .orElseThrow(
                        () -> new CharacteristicsNotFoundException(id));
        characteristicsRepository.delete(characteristics);
    }

    @Override
    public Page<Characteristics> listAllCharacteristics(Pageable pageable) {
        return characteristicsRepository.findAll(pageable);
    }


}
