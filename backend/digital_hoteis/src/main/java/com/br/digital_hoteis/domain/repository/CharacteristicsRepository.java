package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Characteristics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CharacteristicsRepository extends JpaRepository<Characteristics, UUID> {

    @Query("SELECT c FROM Characteristics c WHERE c.id = :id")
    Characteristics findCharacteristicsById(UUID id);


    @Modifying
    @Query("UPDATE Characteristics c SET " +
            "c.name = COALESCE(:name, c.name), " +
            "c.icon = COALESCE(:icon, c.icon) " +
            "WHERE c.id = :id")
    void updateCharacteristicsById(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("icon") String icon);


    @Modifying
    @Query("DELETE FROM Characteristics c WHERE c.id = :id")
    void deleteCharacteristicsById(@Param("id") UUID id);

}