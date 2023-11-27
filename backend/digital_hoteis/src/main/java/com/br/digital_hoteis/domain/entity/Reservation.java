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
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "DATE")
    private LocalDate check_in_date;

    @Column(columnDefinition = "DATE")
    private LocalDate check_out_date;

    @Column(nullable = false)
    private String requests;


    @ManyToOne(optional = false)
    @JoinColumn(name = "guest_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_guest_id"))
    private Guest guest;


    @ManyToMany
    @JoinTable(
            name = "reservation_host",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "host_id")
    )
    private Set<Host> hosts;


//    @MapsId("hostId")
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "host_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_host_id"))
//    private Set<Host> hosts;

//    @MapsId("hotelId")
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "hotel_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_hotel_id"))
//    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reservation_hotel_id"))
    private Hotel hotel;


    private boolean is_canceled;

    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Instant created_At;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Instant updated_At;

    @ManyToMany
    @JoinTable(
            name = "reservation_room",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private Set<Room> rooms;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Set<Host> getHosts() {
        return hosts;
    }

    public void setHosts(Set<Host> hosts) {
        this.hosts = hosts;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getRequests() {
        return requests;
    }

    public void setRequests(String requests) {
        this.requests = requests;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean isIs_canceled() {
        return is_canceled;
    }

    public void setIs_canceled(boolean is_canceled) {
        this.is_canceled = is_canceled;
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", requests='" + requests + '\'' +
                ", guest=" + guest +
                ", hosts=" + hosts +
                ", hotel=" + hotel +
                ", is_canceled=" + is_canceled +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                ", rooms=" + rooms +
                '}';
    }

    public static Reservation newReservation(
            final Set<Room> rooms,
            final Hotel hotel,
            final LocalDate check_in_date,
            final LocalDate check_out_date,
            final String requests,
            final Guest guest,
            final Set<Host> hosts
            ) {
        Reservation reservation = new Reservation();
        reservation.setRooms(rooms);
        reservation.setHotel(hotel);
        reservation.setCheck_in_date(check_in_date);
        reservation.setCheck_out_date(check_out_date);
        reservation.setRequests(requests);
        reservation.setGuest(guest);
        hotel.setHosts(hosts);
        return reservation;
    }

}
