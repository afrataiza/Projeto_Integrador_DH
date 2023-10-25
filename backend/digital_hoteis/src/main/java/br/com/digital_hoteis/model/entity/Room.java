package br.com.digital_hoteis.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @MapsId("hotelId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id", nullable = false, foreignKey = @ForeignKey(name = "fk_room_hotel_id"))
    private Hotel hotel;

    @Column(nullable = false)
    private String description;

    private Integer max_number_of_guests;

    private boolean has_private_bathroom;

    private boolean has_bathtub;

    private boolean has_kitchen;

    private boolean has_stove;

    private boolean has_microwave;

    private boolean are_pets_allowed;

    private BigDecimal price;

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", hotel=" + hotel +
                ", description='" + description + '\'' +
                ", max_number_of_guests=" + max_number_of_guests +
                ", has_private_bathroom=" + has_private_bathroom +
                ", has_bathtub=" + has_bathtub +
                ", has_kitchen=" + has_kitchen +
                ", has_stove=" + has_stove +
                ", has_microwave=" + has_microwave +
                ", are_pets_allowed=" + are_pets_allowed +
                ", price=" + price +
                '}';
    }


}
