package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String street;

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String state;

    @Column(length = 10)
    private String zipcode;

    @Column(length = 60)
    private String country;

    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Instant created_At;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Instant updated_At;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }

    public static City newCity(
            String name,
            String street,
            String district,
            String state,
            String zipcode,
            String country) {
        City city = new City();
        city.setName(name); // erro tava aqui estava setStreet antes
        city.setStreet(street);
        city.setDistrict(district);
        city.setState(state);
        city.setZipcode(zipcode);
        city.setCountry(country);
        return city;
    }
}

