package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Reservation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
public class PageParamsRoomResponse {
    @Getter
    public static class Room {
        private UUID id;

        @Schema(example = "Hotel's details")
        private Hotel hotel;

        @Schema(example = "Reservation details")
        private Reservation reservation;

        @Schema(example = "Description of the room")
        private String description;

        @Schema(example = "Maximum number of guests allowed")
        private Integer max_number_of_guests;

        @Schema(example = "Has a private bathroom")
        private boolean has_private_bathroom;

        @Schema(example = "Has a bathtub")
        private boolean has_bathtub;

        @Schema(example = "Has a kitchen")
        private boolean has_kitchen;

        @Schema(example = "Has a stove")
        private boolean has_stove;

        @Schema(example = "Has a microwave")
        private boolean has_microwave;

        @Schema(example = "Are pets allowed")
        private boolean are_pets_allowed;

        @Schema(example = "Price per night")
        private BigDecimal price;


    }
}

