package br.com.digital_hoteis.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignIn {
    private String email;
    private String password;
}
