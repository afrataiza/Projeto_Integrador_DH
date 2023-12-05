package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID>{
    @Query("SELECT c FROM Contact c WHERE c.id = :id")
    Contact findContactById(UUID id);

    @Modifying
    @Query("UPDATE Contact c SET " +
            "c.email = CASE WHEN :email IS NULL THEN c.email ELSE :email END, " +
            "c.phone_number = CASE WHEN :phone_number IS NULL THEN c.phone_number ELSE :phone_number END " +
            "WHERE c.id = :id")
    void updateContactById(
            @Param("id") UUID id,
            @Param("email") String email,
            @Param("phone_number") String phone_number);

    @Modifying
    @Query("DELETE FROM Contact c WHERE c.id = :id")
    void deleteContactById(@Param("id") UUID id);
}
