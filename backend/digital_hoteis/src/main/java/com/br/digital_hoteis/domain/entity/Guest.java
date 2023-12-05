package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
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
public class Guest extends UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(columnDefinition = "DATE")
    private LocalDate birthdate;

//    @Column(length = 1, nullable = false, columnDefinition = "CHAR(1) DEFAULT 'M'")
//    @NotBlank(message = "The field 'gender' can't be null.")
//    @Pattern(regexp = "[M|F]", message = "The field 'gender' must be filled.")
//    private String gender;


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

    public Guest() {
        super();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
//                ", gender='" + gender + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                ", city=" + city +
                ", contact=" + contact +
                ", hotel=" + hotel +
                ", reservations=" + reservations +
                '}';
    }


    public Guest(String name, String surname, LocalDate birthdate, String gender, City city, Contact contact) {
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
        this.contact = contact;
    }

    public static Guest newGuest(
            String name,
            String surname,
            LocalDate birthdate,
//            String gender,
            City city,
            Contact contact) {
        Guest guest = new Guest();
        guest.setName(name);
        guest.setSurname(surname);
        guest.setBirthdate(birthdate);
//        guest.setGender(gender);
        guest.setCity(city);
        guest.setContact(contact);
        guest.setRole(UserPermissionEnum.USER);
        return guest;
    }


}
