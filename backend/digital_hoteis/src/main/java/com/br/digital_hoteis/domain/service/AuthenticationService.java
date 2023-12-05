package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.app.api.dto.SignIn;
import com.br.digital_hoteis.app.api.dto.SignUp;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> confirmUserAccount(String confirmationToken);

    String signIn(SignIn request);
    String signUp(SignUp request);

    String signIn(SignIn request, String token);
}
