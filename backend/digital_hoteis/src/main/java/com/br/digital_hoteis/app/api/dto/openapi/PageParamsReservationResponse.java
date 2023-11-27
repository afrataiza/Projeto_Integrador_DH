package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.Guest;
import com.br.digital_hoteis.domain.entity.Host;
import com.br.digital_hoteis.domain.entity.Hotel;
import com.br.digital_hoteis.domain.entity.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
public class PageParamsReservationResponse {
    @Getter
    public static class Reservation {
        private UUID id;

        @Schema(example = "2023-09-27T15:30:00Z")
        private Instant created_At;

        @Schema(example = "2023-09-27T16:45:00Z")
        private Instant updated_At;

        @Schema(example = "2023-09-28")
        private LocalDate check_in_date;

        @Schema(example = "2023-09-30")
        private LocalDate check_out_date;

        @Schema(example = "Description of requests regarding the reservation")
        private String requests;

        @Schema(example = "Guest's names and it's details")
        private Guest guest;

        @Schema(example = "Host's names and it's details")
        private Host host;

        @Schema(example = "Hotel's names and it's details")
        private Hotel hotel;

        @Schema(example = "true")
        private boolean is_canceled;

        @Schema(example = "Rooms")
        private Set<Room> rooms;

        public String getRequests() {
            return requests;
        }

        public void setRequests(String requests) {
            this.requests = requests;
        }

        public Set<Room> getRooms() {
            return rooms;
        }

        public void setRooms(Set<Room> rooms) {
            this.rooms = rooms;
        }
    }
}
