package com.br.digital_hoteis.domain.repository;

import com.br.digital_hoteis.domain.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Repository
public interface PolicyRepository extends JpaRepository<Policy, UUID> {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Policy p SET p.check_in_date = :checkInDate, p.check_out_date = :checkOutDate, " +
//            "p.norms_description = :normsDescription, p.health_and_security_description = :healthAndSecurityDescription, " +
//            "p.cancellation_description = :cancellationDescription, p.cancellationPrepaymentConditions = :cancellationPrepaymentConditions, " +
//            "p.refundableDeposit = :refundableDeposit, p.childrenConditions = :childrenConditions, " +
//            "p.cribsExtraBedsConditions = :cribsExtraBedsConditions, p.ageRestriction = :ageRestriction, " +
//            "p.petPolicy = :petPolicy, p.paymentPolicy = :paymentPolicy WHERE p.id = :id")
//    Policy updatePolicyById(UUID id, Map<String, Object> fields);

    @Query("SELECT p FROM Policy p WHERE p.id = :id")
    Policy findPolicyById(UUID id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Policy p WHERE p.id = :id")
    void deletePolicyById(UUID id);
}
