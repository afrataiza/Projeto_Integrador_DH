package br.com.digital_hoteis.model.entity;

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
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "reservation")
    private Set<Room> rooms;

    @Column(columnDefinition = "DATE")
    private LocalDate check_in_date;

    @Column(columnDefinition = "DATE")
    private LocalDate check_out_date;

    @Column(nullable = false)
    private String requests;

    private boolean is_canceled;

    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Instant created_At;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Instant updated_At;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", rooms=" + rooms +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", requests='" + requests + '\'' +
                ", is_canceled=" + is_canceled +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }


}
