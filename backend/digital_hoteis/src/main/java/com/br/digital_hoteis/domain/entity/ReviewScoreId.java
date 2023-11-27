package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewScoreId implements Serializable {

    @Include
    @Column(name = "hotel_id")
    private UUID  hotelId;

    @Include
    @Column(name = "user_detail")
    private UUID  userDetailId;


}
