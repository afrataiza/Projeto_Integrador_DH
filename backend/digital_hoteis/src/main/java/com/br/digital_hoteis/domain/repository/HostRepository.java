package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HostRepository extends JpaRepository<Host, UUID>, JpaSpecificationExecutor<Host> {
    @Query("SELECT h FROM Host h WHERE h.id = :id")
    Host findHostById(UUID id);


    @Modifying
    @Query("UPDATE Host h SET " +
            "h.name = COALESCE(:name, h.name), " +
            "h.surname = COALESCE(:surname, h.surname) " +
            "WHERE h.id = :id")
    void updateHostById(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("surname") String surname);

    @Modifying
    @Query("DELETE FROM Host h WHERE h.id = :id")
    void deleteHostById(@Param("id") UUID id);


}
