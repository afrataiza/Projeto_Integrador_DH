package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID>,
        JpaSpecificationExecutor<City> {

    @Query("SELECT h FROM Hotel h JOIN h.city c WHERE c = :city AND h.isAvailable = true")
    Set<Hotel> findByCity(@Param("city") City city);

    @Query("SELECT c FROM City c WHERE c.id = :id")
    City findCityById(UUID id);

    @Query("SELECT DISTINCT h FROM Hotel h " +
            "JOIN h.city c " +
            "LEFT JOIN h.reservations r " +
            "WHERE c = :city " +
            "AND h.isAvailable = true " +
            "AND (" +
            "(r.id IS NULL) " +
            "OR (r.check_in_date IS NULL OR r.check_out_date IS NULL) " +
            "OR (r.check_out_date < :check_in_date OR r.check_in_date > :check_out_date)" +
            ")")
    Page<Hotel> findAvailableHotelsInCityByDates(
            @Param("city") City city,
            @Param("check_in_date") LocalDate check_in_date,
            @Param("check_out_date") LocalDate check_out_date,
            Pageable page);


    @Modifying
    @Query("UPDATE City c SET " +
            "c.name = CASE WHEN :name IS NULL THEN c.name ELSE :name END, " +
            "c.street = CASE WHEN :street IS NULL THEN c.street ELSE :street END, " +
            "c.district = CASE WHEN :district IS NULL THEN c.district ELSE :district END, " +
            "c.state = CASE WHEN :state IS NULL THEN c.state ELSE :state END, " +
            "c.zipcode = CASE WHEN :zipcode IS NULL THEN c.zipcode ELSE :zipcode END, " +
            "c.country = CASE WHEN :country IS NULL THEN c.country ELSE :country END " +
            "WHERE c.id = :id")
    void updateCityById(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("street") String street,
            @Param("district") String district,
            @Param("state") String state,
            @Param("zipcode") String zipcode,
            @Param("country") String country);


    @Modifying
    @Query("DELETE FROM City c WHERE c.id = :id")
    void deleteCityById(@Param("id") UUID id);
}
