package com.br.digital_hoteis.app.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record AuthenticationSignInRequest(
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password
) {}