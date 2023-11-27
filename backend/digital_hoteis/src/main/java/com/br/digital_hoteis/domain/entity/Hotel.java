package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 65, nullable = false)
    @Size(min = 5, message = "The trading name must have at least 5 characters.")
    private String trading_name;

    private boolean isAvailable;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    private Set<ReviewScore> review_scores = new HashSet<>();

    @CNPJ
    @Column(nullable = false)
    private String cnpj;

    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Instant created_At;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Instant updated_At;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_hotel_category_id")
    private Category category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_hotel_contact_id")
    private Contact contact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_hotel_city_id")
    private City city;

    @OneToMany(mappedBy = "hotel")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "hotel")
    private Set<Host> hosts;

//    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToMany
    @JoinTable(
            name = "hotel_characteristics",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristics_id")
    )
    private Set<Characteristics> characteristics = new HashSet<>();


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Images> images = new HashSet<>();

    @OneToMany(mappedBy = "hotel")
    private Set<Room> rooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return isAvailable() == hotel.isAvailable() &&
                Objects.equals(getId(), hotel.getId()) &&
                Objects.equals(getTrading_name(), hotel.getTrading_name()) &&
                Objects.equals(getReview_score(), hotel.getReview_score()) &&
                Objects.equals(getCnpj(), hotel.getCnpj()) &&
                Objects.equals(getCreated_At(), hotel.getCreated_At()) &&
                Objects.equals(getUpdated_At(), hotel.getUpdated_At()) &&
                Objects.equals(getDescription(), hotel.getDescription()) &&
                Objects.equals(getCategory(), hotel.getCategory()) &&
                Objects.equals(getContact(), hotel.getContact()) &&
                Objects.equals(getCity(), hotel.getCity()) &&
                Objects.equals(getReservations(), hotel.getReservations()) &&
                Objects.equals(getHosts(), hotel.getHosts()) &&
                Objects.equals(getPolicy(), hotel.getPolicy()) &&
                Objects.equals(getCharacteristics(), hotel.getCharacteristics()) &&
                Objects.equals(getImages(), hotel.getImages()) &&
                Objects.equals(getRooms(), hotel.getRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getTrading_name(),
                isAvailable(),
                getReview_score(),
                getCnpj(),
                getCreated_At(),
                getUpdated_At(),
                getDescription(),
                getCategory(),
                getContact(),
                getCity(),
                getReservations(),
                getHosts(),
                getPolicy(),
                getCharacteristics(),
                getImages(),
                getRooms());
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Set<ReviewScore> getReview_score() {
        return review_scores;
    }

    public void setReview_score(Set<ReviewScore> review_scores) {
        this.review_scores = review_scores;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Characteristics> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Set<Characteristics> characteristics) {
        this.characteristics = characteristics;
    }

    public Set<Images> getImages() {
        return images;
    }

    public void setImages(Set<Images> images) {
        this.images = images;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", trading_name='" + trading_name + '\'' +
                ", isAvailable=" + isAvailable +
                ", review_scores=" + review_scores +
                ", cnpj='" + cnpj + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", contact=" + contact +
                ", city=" + city +
                ", reservations=" + reservations +
                ", hosts=" + hosts +
                ", policy=" + policy +
                ", characteristics=" + characteristics +
                ", images=" + images +
                ", rooms=" + rooms +
                '}';
    }

    public static Hotel newHotel(
            String trading_name,
            Set<ReviewScore> review_scores,
            String cnpj,
            String description,
            Category category,
            Contact contact,
            City city,
            Set<Host> hosts,
            Policy policy,
            Set<Characteristics> characteristics,
            Set<Images> images,
            Set<Room> rooms){
        Hotel hotel = new Hotel();
        hotel.setTrading_name(trading_name);
        hotel.setReview_score(review_scores);
        hotel.setCnpj(cnpj);
        hotel.setDescription(description);
        hotel.setCategory(category);
        hotel.setContact(contact);
        hotel.setCity(city);
        hotel.setHosts(hosts);
        hotel.setPolicy(policy);
        hotel.setCharacteristics(characteristics);
        hotel.setImages(images);
        hotel.setRooms(rooms);
        return hotel;
    }
}

