package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Getter
public class PageParamsGuestResponse {
    @Getter
    public static class Guest {
        @Schema(description = "The unique identifier for the guests")
        private UUID id;

        @Schema(example = "Guest's name")
        private String name;

        @Schema(example = "Guest's surname")
        private String surname;

        @Schema(example = "1990-01-01")
        private LocalDate birthdate;

        private City city;
        private Contact contact;

//        @Schema(example = "M")
//        private String gender;

        @Schema(example = "2023-09-27T15:30:00Z")
        private Instant created_At;

        @Schema(example = "2023-09-27T16:45:00Z")
        private Instant updated_At;

        public City getCity() {
            return city;
        }
        public void setCity(City city) {
            this.city = city;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }
    }
}
