package com.br.digital_hoteis.app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignIn {
    private String email;
    private String password;
}