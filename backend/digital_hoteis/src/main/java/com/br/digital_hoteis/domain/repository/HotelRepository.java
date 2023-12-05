package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Characteristics;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.ReviewScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID>,
        JpaSpecificationExecutor<Hotel> {

    @Query("SELECT h FROM Hotel h WHERE h.id = :id")
    Hotel findHotelById(UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Hotel h SET " +
            "h.trading_name = CASE WHEN :trading_name IS NULL THEN h.trading_name ELSE :trading_name END, " +
            "h.cnpj = CASE WHEN :cnpj IS NULL THEN h.cnpj ELSE :cnpj END, " +
            "h.description = CASE WHEN :description IS NULL THEN h.description ELSE :description END " +
            "WHERE h.id = :id")
    void updateHotelById(
            @Param("id") UUID id,
            @Param("trading_name") String trading_name,
            @Param("cnpj") String cnpj,
            @Param("description") String description);

    @Modifying
    @Transactional
    @Query("DELETE FROM Hotel h WHERE h.id = :id")
    void deleteHotelById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Hotel h SET h.review_scores = :review_scores WHERE h.id = :hotel_id")
    void addReviewScore(@Param("hotel_id") UUID hotel_id, @Param("review_scores") Set<ReviewScore> review_scores);

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.characteristics WHERE h.id = :id")
    Hotel findHotelByIdWithCharacteristics(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("UPDATE Hotel h SET h.characteristics = :characteristics WHERE h.id = :hotel_id")
    void addCharacteristics(@Param("hotel_id") UUID hotel_id, @Param("characteristics") Set<Characteristics> characteristics);

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.policy WHERE h.id = :id")
    Hotel findHotelWithPolicyById(@Param("id") UUID id);



}



