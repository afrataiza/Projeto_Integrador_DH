package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Characteristics;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Policy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface HotelService {

    Page<Hotel> findHotelByCityId(UUID cityId, Pageable page);
    Page<Hotel> findAllHotels(Pageable page);
    Hotel findHotelById(UUID id);
    Hotel createHotel(Hotel hotel);
    void updateHotel(UUID id, Map<String, Object> fields);
    void deleteHotelById(UUID id);
    Page<Hotel> findHotelsByCategoryId(UUID hotel_id, Pageable page);
    void addReviewScore(UUID hotel_id, UUID user_id, Integer review_score);
    void addCharacteristics(UUID hotel_id, Characteristics characteristics);
    void addPoliciesToAHotelById(UUID hotel_id, Policy policy);

    Hotel getHotelWithPolicy(UUID hotel_id);

    Policy getPolicyByHotelId(UUID hotel_id);
   Map<String, Object> getHotelWithPolicyDetails(UUID hotel_id);
}