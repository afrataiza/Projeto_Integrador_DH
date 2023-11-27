package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.ReviewScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ReviewScoreRepository extends JpaRepository<ReviewScore, UUID> {

    Set<ReviewScore> findByHotelId(UUID hotel_id);

    @Query("SELECT AVG(rs.review_scores) FROM ReviewScore rs WHERE rs.id.hotelId = :hotel_id")
    Double calculateAverageReviewScore(@Param("hotel_id") UUID hotel_id);

    @Query("SELECT rs FROM ReviewScore rs WHERE rs.review_scores BETWEEN :minRating AND :maxRating")
    List<ReviewScore> findByReviewScoreBetween(@Param("minRating") Integer minRating,
                                               @Param("maxRating") Integer maxRating);

    @Query("SELECT DISTINCT rs.id.hotelId FROM ReviewScore rs WHERE rs.review_scores BETWEEN :minRating AND :maxRating")
    List<UUID> findHotelIdsByReviewScoreBetween(@Param("minRating") Integer minRating,
                                                @Param("maxRating") Integer maxRating);

    @Query("SELECT MIN(rs.review_scores) FROM ReviewScore rs WHERE rs.id.hotelId = :hotel_id")
    Integer findMinReviewScoreByHotelId(@Param("hotel_id") UUID hotel_id);

    @Query("SELECT MAX(rs.review_scores) FROM ReviewScore rs WHERE rs.id.hotelId = :hotel_id")
    Integer findMaxReviewScoreByHotelId(@Param("hotel_id") UUID hotel_id);
}
