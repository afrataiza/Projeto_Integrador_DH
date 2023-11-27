package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Characteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface CharacteristicsService {

    Page<Characteristics> findAllCharacteristics(Pageable pageable);
    Characteristics createCharacteristics(Characteristics characteristics);
    Characteristics findCharacteristicsById(UUID id);
    Characteristics updateCharacteristicsById(UUID id, Map<String, Object> fields);
    void deleteCharacteristicsById(UUID id);
    Page<Characteristics> listAllCharacteristics(Pageable pageable);

}
