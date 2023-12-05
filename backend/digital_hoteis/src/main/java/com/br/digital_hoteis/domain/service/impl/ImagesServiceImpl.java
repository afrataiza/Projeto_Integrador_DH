package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Images;
import com.br.digital_hoteis.domain.exception.ImageNotFoundException;
import com.br.digital_hoteis.domain.repository.ImagesRepository;
import com.br.digital_hoteis.domain.service.ImagesService;
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
public class ImagesServiceImpl implements ImagesService {

    private final ImagesRepository imagesRepository;
    private final ObjectMapper mapper;
    @Override
    public Page<Images> findAllImages(Pageable pageable) {
        return imagesRepository.findAll(pageable);
    }

    @Override
    public Images createImages(Images images) {
        return imagesRepository.save(images);
    }

    @Override
    public Images findImagesById(UUID id) {
        return imagesRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
    }

    @Override
    public Images updateImagesById(UUID id, Map<String, Object> fields) {
        Images images = imagesRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
        Images input = mapper.convertValue(fields, Images.class);
        fields.forEach((property, value) -> {
            Field field = findField(Images.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, images, newValue);
        });
        return imagesRepository.save(images);
    }

    @Override
    public void deleteImagesById(UUID id) {
        Images images = imagesRepository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException(id));
        imagesRepository.delete(images);
    }

    @Override
    public Page<Images> listAllImages(Pageable pageable) {
        return imagesRepository.findAll(pageable);
    }
}
