package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.ImagesApi;
import com.br.digital_hoteis.app.api.dto.request.CreateHotelRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateImageRequest;
import com.br.digital_hoteis.app.api.dto.response.ImagesDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.ImagesSummaryResponse;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Images;
import com.br.digital_hoteis.domain.service.HotelService;
import com.br.digital_hoteis.domain.service.ImagesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@Tag(name = "Images")
@RestController
@RequestMapping("v1/images")
public class ImagesController implements ImagesApi {

    private final ImagesService imagesService;
    private final HotelService hotelService;

    public ImagesController(ImagesService imagesService, HotelService hotelService) {
        this.imagesService = imagesService;
        this.hotelService = hotelService;
    }

    @Override
    public ResponseEntity<Page<ImagesSummaryResponse>> findAllImages(
            @PageableDefault Pageable page) {
        Page<Images> imagesPage = imagesService.findAllImages(page);
        Page<ImagesSummaryResponse> response = imagesPage
                .map(images -> new ImagesSummaryResponse(
                        images.getId(),
                        images.getUrl(),
                        images.getHotel().getId()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ImagesDetailedResponse> findImagesById(@PathVariable UUID imageId) {
        Images images = imagesService.findImagesById(imageId);
        if (images == null) {
            return ResponseEntity.notFound().build();
        }
        ImagesDetailedResponse response = new ImagesDetailedResponse(
                images.getId(),
                images.getTitle(),
                images.getUrl(),
                images.getHotel().getId());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Images> createImages(@RequestBody @Valid CreateImageRequest request, CreateHotelRequest hotelRequest) {

        Hotel hotel = hotelService.findHotelById(request.hotelId());

        if (hotel == null) {
            return ResponseEntity.notFound().build();
        }

        Images images = new Images();
        images.setTitle(request.title());
        images.setUrl(request.url());
        images.setHotel(hotel);

        Images createdImages = imagesService.createImages(images);
        return ResponseEntity.ok(createdImages);
    }


    @Override
    public ResponseEntity<Images> updateImagesById(@PathVariable UUID imageId,
                                                   @RequestBody Map<String, Object> fields) {
        Images updatedImages = imagesService.updateImagesById(imageId, fields);
        return ResponseEntity.ok(updatedImages);
    }

    @Override
    public ResponseEntity<Void> deleteImagesById(@PathVariable UUID imageId) {
        imagesService.deleteImagesById(imageId);
        return ResponseEntity.noContent().build();
    }
}
