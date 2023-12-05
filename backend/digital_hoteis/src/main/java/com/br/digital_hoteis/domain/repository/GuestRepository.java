package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID>,
        JpaSpecificationExecutor<Guest> {

    @Query("SELECT g FROM Guest g WHERE g.id = :id")
    Guest findGuestById(UUID id);


    @Modifying
    @Query("UPDATE Guest g SET " +
            "g.name = CASE WHEN :name IS NULL THEN g.name ELSE :name END, " +
            "g.surname = CASE WHEN :surname IS NULL THEN g.surname ELSE :surname END, " +
            "g.birthdate = CASE WHEN :birthdate IS NULL THEN g.birthdate ELSE :birthdate END, " +
            "g.gender = CASE WHEN :gender IS NULL THEN g.gender ELSE :gender END " +
            "WHERE g.id = :id")
    void updateGuestById(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("birthdate") LocalDate birthdate,
            @Param("gender") String gender);

    @Modifying
    @Query("DELETE FROM Guest g WHERE g.id = :id")
    void deleteGuestById(@Param("id") UUID id);
}
