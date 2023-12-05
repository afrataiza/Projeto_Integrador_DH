package com.br.digital_hoteis.app.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record AuthenticationSignUpRequest(
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank @Email String email,
        @NotBlank String retype_email,
        @NotBlank @Size(min = 6) String password,
        boolean isHost
) {}