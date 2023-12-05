package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.CharacteristicsApi;
import com.br.digital_hoteis.app.api.dto.request.CreateCharacteristicsRequest;
import com.br.digital_hoteis.app.api.dto.response.CharacteristicsDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.CharacteristicsSummaryResponse;
import com.br.digital_hoteis.domain.entity.Characteristics;
import com.br.digital_hoteis.domain.service.CharacteristicsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.UUID;
@RestController
@AllArgsConstructor
public class CharacteristicsController implements CharacteristicsApi {

    private final CharacteristicsService characteristicsService;


    @Override
    public ResponseEntity<Page<CharacteristicsSummaryResponse>> findCharacteristics(Pageable page) {
        Page<Characteristics> pageCharacteristics = characteristicsService.listAllCharacteristics(page);
        Page<CharacteristicsSummaryResponse> response = pageCharacteristics
                .map(characteristics -> new CharacteristicsSummaryResponse(characteristics.getId(), characteristics.getName()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CharacteristicsDetailedResponse> findCharacteristicsById(UUID characteristicsId) {
        Characteristics characteristics = characteristicsService.findCharacteristicsById(characteristicsId);

        CharacteristicsDetailedResponse response = new CharacteristicsDetailedResponse(
                characteristics.getId(),
                characteristics.getName(),
                characteristics.getIcon()
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> createCharacteristics(@RequestBody @Valid CreateCharacteristicsRequest request) {

        Characteristics characteristics = Characteristics.newCharacteristics(
                request.name(),
                request.icon()
        );

        Characteristics createdCharacteristics = characteristicsService.createCharacteristics(characteristics);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdCharacteristics.getId()).toUri()).build();
    }

    @Override
    public ResponseEntity<Void> updateCharacteristicsById(UUID characteristicsId, Map<String, Object> fields) {
        characteristicsService.updateCharacteristicsById(characteristicsId,fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteCharacteristicsById(UUID characteristicsId) {
        characteristicsService.deleteCharacteristicsById(characteristicsId);
        return ResponseEntity.noContent().build();
    }
}