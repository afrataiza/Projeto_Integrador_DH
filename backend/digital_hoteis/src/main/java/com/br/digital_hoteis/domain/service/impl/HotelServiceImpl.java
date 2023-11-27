package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Characteristics;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Policy;
import com.br.digital_hoteis.domain.entity.ReviewScore;
import com.br.digital_hoteis.domain.exception.HotelNotFoundException;
import com.br.digital_hoteis.domain.repository.CityRepository;
import com.br.digital_hoteis.domain.repository.HotelRepository;
import com.br.digital_hoteis.domain.repository.ReviewScoreRepository;
import com.br.digital_hoteis.domain.repository.specification.CategorySpecification;
import com.br.digital_hoteis.domain.repository.specification.HotelSpecification;
import com.br.digital_hoteis.domain.service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;
@Slf4j
@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;
    private final ReviewScoreRepository reviewScoreRepository;
    private final ReviewScoreServiceImpl reviewScoreService;
    private final ObjectMapper mapper;


    @Override
    public Page<Hotel> findHotelByCityId(UUID id, Pageable page) {
        return hotelRepository.findAll(HotelSpecification.byCityId(id), page);
    }

    @Override
    public Page<Hotel> findAllHotels(Pageable page) {
        return hotelRepository.findAll(page);
    }

    @Override
    public Hotel findHotelById(UUID id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public void updateHotel(UUID id, Map<String, Object> fields) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
        Hotel input = mapper.convertValue(fields, Hotel.class);
        fields.forEach((property, value) -> {
            Field field = findField(Hotel.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, hotel, newValue);
        });
        hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotelById(UUID id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));
        hotelRepository.delete(hotel);
    }

    @Override
    public Page<Hotel> findHotelsByCategoryId(UUID hotel_id, Pageable page) {
        return hotelRepository.findAll(CategorySpecification.byHotelId(hotel_id), page);
    }



    @Override
    public void addReviewScore(UUID hotel_id, UUID user_id, Integer review_scores) {
        if (review_scores < 1 || review_scores > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new HotelNotFoundException(hotel_id));

        if (hotel.getReview_scores() == null) {
            hotel.setReview_scores(new HashSet<>());
        }

        ReviewScore reviewScore = ReviewScore.newReviewScore(hotel_id, user_id, review_scores);

        reviewScoreService.setHotelForReviewScore(hotel, reviewScore);

        hotelRepository.save(hotel);
    }

    public void addCharacteristics(UUID hotel_id, Characteristics characteristics) {
        Hotel hotel = hotelRepository.findHotelByIdWithCharacteristics(hotel_id);

        if (hotel != null) {
            hotel.getCharacteristics().add(characteristics);
            hotelRepository.save(hotel);
        } else {
          hotelRepository.findById(hotel_id)
                    .orElseThrow(() -> new HotelNotFoundException(hotel_id));
        }
    }

    @Override
    public void addPoliciesToAHotelById(UUID hotel_id, Policy policy) {
        Hotel hotel = hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new HotelNotFoundException(hotel_id));

        hotel.setPolicy(policy);
        hotelRepository.save(hotel);
        }



//    @Override
//    public Hotel getHotelWithPolicy(UUID hotel_id) {
//        return hotelRepository.findById(hotel_id)
//                .orElseThrow(() -> new HotelNotFoundException(hotel_id));
//    }
//    public Policy getPolicyByHotelId(UUID hotel_id) {
//        Hotel hotel = getHotelWithPolicy(hotel_id);
//        return hotel.getPolicy();
//    }

    @Override
    public Hotel getHotelWithPolicy(UUID hotel_id) {
        return hotelRepository.findById(hotel_id)
                .orElseThrow(() -> new HotelNotFoundException(hotel_id));
    }

    public Policy getPolicyByHotelId(UUID hotel_id) {
        Hotel hotelWithPolicy = getHotelWithPolicy(hotel_id);


        Policy policy = hotelWithPolicy.getPolicy();

        if (policy == null) {
            throw new HotelNotFoundException(hotel_id);
        }

        return policy;
    }



    @Override
    public Map<String, Object> getHotelWithPolicyDetails(UUID hotel_id) {
        Hotel hotel = getHotelWithPolicy(hotel_id);


        Policy policy = hotel.getPolicy();

        if (policy == null) {
            throw new HotelNotFoundException(hotel_id);
        }

        Map<String, Object> hotelWithPolicyDetails = new HashMap<>();
        hotelWithPolicyDetails.put("hotel", hotel);
        hotelWithPolicyDetails.put("policy", policy);

        return hotelWithPolicyDetails;
    }



}
