package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface ImagesService {

    Page<Images> findAllImages(Pageable pageable);
    Images createImages(Images images);
    Images findImagesById(UUID id);
    Images updateImagesById(UUID id, Map<String, Object> fields);
    void deleteImagesById(UUID id);
    Page<Images> listAllImages(Pageable pageable);
}
