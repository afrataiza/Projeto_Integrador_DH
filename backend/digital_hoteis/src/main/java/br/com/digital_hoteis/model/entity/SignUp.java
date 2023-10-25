package br.com.digital_hoteis.model.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class SignUp {
    private String name;
    private String email;
    private String password;
    private LocalDate birthdate;
    private UserPermissionEnum role;
}
