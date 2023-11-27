package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
@Getter
public class PageParamsHostResponse {
    @Getter
    public static class Host {
        private UUID id;

        @Schema(example = "Host's name")
        private String name;

        @Schema(example = "Host's surname")
        private String surname;

        @Schema(example = "1990-01-01")
        private LocalDate birthdate;

        @Schema(example = "M")
        private String gender;

        @Schema(example = "2023-09-27T15:30:00Z")
        private Instant created_At;

        @Schema(example = "2023-09-27T16:45:00Z")
        private Instant updated_At;

        private Contact contact;

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }
    }
}
