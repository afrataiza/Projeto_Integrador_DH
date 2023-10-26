package br.com.digital_hoteis.model.entity;

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
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100)
    private String street;

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String city;

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
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }

    public static Address newAddress(
            String street,
            String district,
            String city,
            String state,
            String zipcode,
            String country) {
        Address address = new Address();
        address.setStreet(street);
        address.setDistrict(district);
        address.setCity(city);
        address.setStreet(state);
        address.setZipcode(zipcode);
        address.setCountry(country);
        return address;
    }
}
