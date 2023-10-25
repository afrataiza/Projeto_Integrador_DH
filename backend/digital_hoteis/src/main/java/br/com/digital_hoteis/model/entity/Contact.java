package br.com.digital_hoteis.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    @Column(length = 100, nullable = false)
    private String email;

    @Column
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Invalid telephone number. Please use the following format (xx) xxxxx-xxxx.")
    private String phone_number;

    @CreatedDate
    @Column(columnDefinition = "DATETIME")
    private Instant created_At;

    @LastModifiedDate
    @Column(columnDefinition = "DATETIME")
    private Instant updated_At;

    public static Contact newContact(
            String email,
            String phone_number) {
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setPhone_number(phone_number);
        return contact;
    }
}
