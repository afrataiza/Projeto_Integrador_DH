package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "review_score")
public class ReviewScore{

    @EmbeddedId
    private ReviewScoreId id;

    @MapsId("hotelId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_hotel_id"))
    private Hotel hotel;

    @MapsId("userDetailId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_detail_id", nullable = false, foreignKey = @ForeignKey(name = "fk_review_user_details"))
    private UserDetail userDetail;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer review_scores;


    public static ReviewScore newReviewScore(UUID hotelId, UUID userId, Integer reviewScores) {
        ReviewScore reviewScore = new ReviewScore();
        reviewScore.id.setHotelId(hotelId);
        reviewScore.id.setUserDetailId(userId);
        reviewScore.setReview_scores(reviewScores);
        return reviewScore;
    }
}
