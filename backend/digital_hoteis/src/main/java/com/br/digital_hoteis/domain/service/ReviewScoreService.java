package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.app.api.dto.openapi.PageParamsHotelResponse;
import com.br.digital_hoteis.domain.entity.ReviewScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface ReviewScoreService {

    ReviewScore findReviewScoreById(UUID id);
    ReviewScore updateReviewScore(UUID id, Map<String, Object> fields);
    void deleteReviewScoreById(UUID id);
    Page<PageParamsHotelResponse.Hotel> findHotelsByReviewScoresRange(Integer minRating, Integer maxRating, Pageable page);
    ReviewScore createReviewScore(ReviewScore review_score);
    Set<ReviewScore> calculateAverageReviewScore(UUID hotelId);

}
