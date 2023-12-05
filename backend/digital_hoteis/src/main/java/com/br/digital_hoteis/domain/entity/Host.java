package com.br.digital_hoteis.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Host extends UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 35, nullable = false)
    private String name;

    @Column(length = 65, nullable = false)
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
            name = "contact_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_host_contact_id"))
    private Contact contact;

    @ManyToOne
    @JoinColumn(
            name = "hotel_id",
            referencedColumnName = "id",
            foreignKey =
            @ForeignKey(name = "fk_host_hotel_id"))
    private Hotel hotel;

    public static Host newHost(
            final String name,
            final String surname,
            final LocalDate birthdate,
//            final String gender,
            final Contact contact
    ) {
        Host host = new Host();
        host.setName(name);
        host.setSurname(surname);
        host.setBirthdate(birthdate);
//        host.setGender(gender);
        host.setHost(true);
        host.setContact(contact);
        return host;
    }
}


