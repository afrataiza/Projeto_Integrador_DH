package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsHotelResponse;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.ReviewScore;
import com.br.digital_hoteis.domain.exception.ReviewScoreNotFoundException;
import com.br.digital_hoteis.domain.repository.HotelRepository;
import com.br.digital_hoteis.domain.repository.ReviewScoreRepository;
import com.br.digital_hoteis.domain.service.ReviewScoreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;

@Slf4j
@Service
@AllArgsConstructor
public class ReviewScoreServiceImpl implements ReviewScoreService {

    private final ReviewScoreRepository reviewScoreRepository;
    private final HotelRepository hotelRepository;
    private final ObjectMapper mapper;


    @Override
    public ReviewScore findReviewScoreById(UUID id) {
        return reviewScoreRepository.findById(id)
                .orElseThrow(() -> new ReviewScoreNotFoundException(id));
    }

    @Override
    public ReviewScore updateReviewScore(UUID id, Map<String, Object> fields) {
        ReviewScore reviewScore = reviewScoreRepository.findById(id)
                .orElseThrow(() -> new ReviewScoreNotFoundException(id));
        ReviewScore input = mapper.convertValue(fields, ReviewScore.class);
        fields.forEach((property, value) -> {
            Field field = findField(ReviewScore.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, reviewScore, newValue);
        });
        return reviewScoreRepository.save(reviewScore);
    }

    @Override
    public void deleteReviewScoreById(UUID id) {
        ReviewScore reviewScore = reviewScoreRepository.findById(id)
                .orElseThrow(() -> new ReviewScoreNotFoundException(id));
        reviewScoreRepository.delete(reviewScore);
    }

        @Override
        public ReviewScore createReviewScore(ReviewScore review_score) {
            return reviewScoreRepository.save(review_score);
        }

    public Set<ReviewScore> calculateAverageReviewScore(UUID hotelId) {
        Set<ReviewScore> reviewScores = reviewScoreRepository.findByHotelId(hotelId);

        if (reviewScores.isEmpty()) {
            return Collections.emptySet();
        }

        int totalScore = reviewScores.stream().mapToInt(ReviewScore::getReview_scores).sum();
        double averageScore = (double) totalScore / reviewScores.size();

        log.info("Average review score for hotel {}: {}", hotelId, averageScore);
        return reviewScores;
    }


    @Override
    public Page<PageParamsHotelResponse.Hotel> findHotelsByReviewScoresRange(Integer minRating, Integer maxRating, Pageable page) {
        List<UUID> hotelIds = reviewScoreRepository.findHotelIdsByReviewScoreBetween(minRating, maxRating);

        List<PageParamsHotelResponse.Hotel> hotels = hotelIds.stream()
                .map(hotel_id -> hotelRepository.findById(hotel_id)
                        .map(hotel -> {
                            PageParamsHotelResponse.Hotel responseHotel = new PageParamsHotelResponse.Hotel(
                                    hotel.getCategory(),
                                    hotel.getContact(),
                                    hotel.getCity()
                            );
                            responseHotel.setId(hotel.getId());
                            responseHotel.setTrading_name(hotel.getTrading_name());
                            responseHotel.setCnpj(hotel.getCnpj());
                            responseHotel.setCreated_At(hotel.getCreated_At());
                            responseHotel.setUpdated_At(hotel.getUpdated_At());
                            responseHotel.setDescription(hotel.getDescription());
                            responseHotel.setHosts(hotel.getHosts());
                            return responseHotel;
                        })
                        .orElse(null))
                .collect(Collectors.toList());

        int start = (int) page.getOffset();
        int end = Math.min((start + page.getPageSize()), hotels.size());

        return new PageImpl<>(hotels.subList(start, end), page, hotels.size());
    }

    public void setHotelForReviewScore(Hotel hotel, ReviewScore reviewScore) {

        reviewScore.setHotel(hotel);
        reviewScoreRepository.save(reviewScore);
    }

}



