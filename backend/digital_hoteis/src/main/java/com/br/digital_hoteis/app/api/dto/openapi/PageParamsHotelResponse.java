package com.br.digital_hoteis.app.api.dto.openapi;

import com.br.digital_hoteis.domain.entity.City;
import com.br.digital_hoteis.domain.entity.Category;
import com.br.digital_hoteis.domain.entity.Contact;
import com.br.digital_hoteis.domain.entity.Host;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
@Getter
public class PageParamsHotelResponse {
    @Getter
    public static class Hotel {
        private UUID id;

        @Schema(example = "Hotel's trading name")
        private String trading_name;

        @Schema(example = "CNPJ")
        private String cnpj;

        @Schema(example = "2023-09-27T15:30:00Z")
        private Instant created_At;

        @Schema(example = "2023-09-27T16:45:00Z")
        private Instant updated_At;

        // testando
        private String description;
        private final Category category;
        private final Contact contact;
        private final City city;
        private Set<Host> hosts;

        public Hotel(Category category, Contact contact, City city) {
            this.category = category;
            this.contact = contact;
            this.city = city;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Set<Host> getHosts() {
            return hosts;
        }

        public void setHosts(Set<Host> hosts) {
            this.hosts = hosts;
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getTrading_name() {
            return trading_name;
        }

        public void setTrading_name(String trading_name) {
            this.trading_name = trading_name;
        }

        public String getCnpj() {
            return cnpj;
        }

        public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
        }

        public Instant getCreated_At() {
            return created_At;
        }

        public void setCreated_At(Instant created_At) {
            this.created_At = created_At;
        }

        public Instant getUpdated_At() {
            return updated_At;
        }

        public void setUpdated_At(Instant updated_At) {
            this.updated_At = updated_At;
        }

        public Category getCategory() {
            return category;
        }

        public Contact getContact() {
            return contact;
        }

        public City getCity() {
            return city;
        }
    }
}
