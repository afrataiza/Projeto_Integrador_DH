package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;

    @Column(length = 1, nullable = false)
    @NotBlank(message = "The field 'gender' can't be null.")
    @Pattern(regexp = "[M|F]", message = "The field 'gender' must be filled.")
    private String gender;

    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Instant created_At;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Instant updated_At;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "city_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_guest_city_id"))
    private City city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "contact_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_guest_contact_id"))
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "guest")
    private Set<Reservation> reservations;

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                ", city=" + city +
                ", contact=" + contact +
                ", hotel=" + hotel +
                ", reservations=" + reservations +
                '}';
    }

    public static Guest newGuest(
            String name,
            String surname,
            LocalDate birthdate,
            String gender,
            City city,
            Contact contact) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setSurname(surname);
        guest.setBirthdate(birthdate);
        guest.setGender(gender);
        guest.setCity(city);
        guest.setContact(contact);
        return guest;
    }
}
