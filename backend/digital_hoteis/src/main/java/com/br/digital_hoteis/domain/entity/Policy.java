package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "policy")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "policy")
    private Set<Hotel> hotels = new HashSet<>();

    @Column(columnDefinition = "DATE")
    private LocalDate check_in_date;

    @Column(columnDefinition = "DATE")
    private LocalDate check_out_date;

    @Column(columnDefinition = "TEXT")
    private String norms_description;

    @Column(columnDefinition = "TEXT")
    private String health_and_security_description;

    @Column(columnDefinition = "TEXT")
    private String cancellation_description;

    private String cancellationPrepaymentConditions;

    private String refundableDeposit;

    private String childrenConditions;

    private String cribsExtraBedsConditions;

    private String ageRestriction;

    private String petPolicy;

    private String paymentPolicy;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getCancellationPrepaymentConditions() {
        return cancellationPrepaymentConditions;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }

    public String getNorms_description() {
        return norms_description;
    }

    public void setNorms_description(String norms_description) {
        this.norms_description = norms_description;
    }

    public String getHealth_and_security_description() {
        return health_and_security_description;
    }

    public void setHealth_and_security_description(String health_and_security_description) {
        this.health_and_security_description = health_and_security_description;
    }

    public String getCancellation_description() {
        return cancellation_description;
    }

    public void setCancellation_description(String cancellation_description) {
        this.cancellation_description = cancellation_description;
    }

    public void setCancellationPrepaymentConditions(String cancellationPrepaymentConditions) {
        this.cancellationPrepaymentConditions = cancellationPrepaymentConditions;
    }

    public String getRefundableDeposit() {
        return refundableDeposit;
    }

    public void setRefundableDeposit(String refundableDeposit) {
        this.refundableDeposit = refundableDeposit;
    }

    public String getChildrenConditions() {
        return childrenConditions;
    }

    public void setChildrenConditions(String childrenConditions) {
        this.childrenConditions = childrenConditions;
    }

    public String getCribsExtraBedsConditions() {
        return cribsExtraBedsConditions;
    }

    public void setCribsExtraBedsConditions(String cribsExtraBedsConditions) {
        this.cribsExtraBedsConditions = cribsExtraBedsConditions;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getPetPolicy() {
        return petPolicy;
    }

    public void setPetPolicy(String petPolicy) {
        this.petPolicy = petPolicy;
    }

    public String getPaymentPolicy() {
        return paymentPolicy;
    }

    public void setPaymentPolicy(String paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy policy)) return false;
        return Objects.equals(getId(), policy.getId()) &&
                Objects.equals(getCheck_in_date(), policy.getCheck_in_date()) &&
                Objects.equals(getCheck_out_date(), policy.getCheck_out_date()) &&
                Objects.equals(getNorms_description(), policy.getNorms_description()) &&
                Objects.equals(getHealth_and_security_description(), policy.getHealth_and_security_description()) &&
                Objects.equals(getCancellation_description(), policy.getCancellation_description()) &&
                Objects.equals(getCancellationPrepaymentConditions(), policy.getCancellationPrepaymentConditions()) &&
                Objects.equals(getRefundableDeposit(), policy.getRefundableDeposit()) &&
                Objects.equals(getChildrenConditions(), policy.getChildrenConditions()) &&
                Objects.equals(getCribsExtraBedsConditions(), policy.getCribsExtraBedsConditions()) &&
                Objects.equals(getAgeRestriction(), policy.getAgeRestriction()) &&
                Objects.equals(getPetPolicy(), policy.getPetPolicy()) &&
                Objects.equals(getPaymentPolicy(), policy.getPaymentPolicy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getCheck_in_date(),
                getCheck_out_date(),
                getNorms_description(),
                getHealth_and_security_description(),
                getCancellation_description(),
                getCancellationPrepaymentConditions(),
                getRefundableDeposit(),
                getChildrenConditions(),
                getCribsExtraBedsConditions(),
                getAgeRestriction(),
                getPetPolicy(),
                getPaymentPolicy());
    }


    public static Policy newPolicy(
                  LocalDate check_in_date,
                  LocalDate check_out_date,
                  String norms_description,
                  String health_and_security_description,
                  String cancellation_description,
                  String cancellationPrepaymentConditions,
                  String refundableDeposit,
                  String childrenConditions,
                  String cribsExtraBedsConditions,
                  String ageRestriction,
                  String petPolicy,
                  String paymentPolicy) {

        Policy policy = new Policy();
        policy.setCheck_in_date(check_in_date);
        policy.setCheck_out_date(check_out_date);
        policy.setNorms_description(norms_description);
        policy.setHealth_and_security_description(health_and_security_description);
        policy.setCancellation_description(cancellation_description);
        policy.setCancellationPrepaymentConditions(cancellationPrepaymentConditions);
        policy.setRefundableDeposit(refundableDeposit);
        policy.setChildrenConditions(childrenConditions);
        policy.setCribsExtraBedsConditions(cribsExtraBedsConditions);
        policy.setAgeRestriction(ageRestriction);
        policy.setPetPolicy(petPolicy);
        policy.setPaymentPolicy(paymentPolicy);
        return policy;
    }


}
