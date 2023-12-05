package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    @Modifying
    @Query("UPDATE Room r SET " +
            "r.description = CASE WHEN :description IS NULL THEN r.description ELSE :description END, " +
            "r.max_number_of_guests = CASE WHEN :maxNumberOfGuests IS NULL THEN r.max_number_of_guests ELSE :maxNumberOfGuests END, " +
            "r.has_private_bathroom = :hasPrivateBathroom, " +
            "r.has_bathtub = :hasBathtub, " +
            "r.has_kitchen = :hasKitchen, " +
            "r.has_stove = :hasStove, " +
            "r.has_microwave = :hasMicrowave, " +
            "r.are_pets_allowed = :arePetsAllowed, " +
            "r.price = CASE WHEN :price IS NULL THEN r.price ELSE :price END " +
            "WHERE r.id = :id")
    void updateById(
            @Param("id") UUID id,
            @Param("description") String description,
            @Param("maxNumberOfGuests") Integer maxNumberOfGuests,
            @Param("hasPrivateBathroom") boolean hasPrivateBathroom,
            @Param("hasBathtub") boolean hasBathtub,
            @Param("hasKitchen") boolean hasKitchen,
            @Param("hasStove") boolean hasStove,
            @Param("hasMicrowave") boolean hasMicrowave,
            @Param("arePetsAllowed") boolean arePetsAllowed,
            @Param("price") BigDecimal price);


}

