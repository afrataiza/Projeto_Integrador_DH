package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.app.api.dto.SignIn;
import com.br.digital_hoteis.app.api.dto.SignUp;

public interface AuthenticationService {
    String signIn(SignIn request);
    String signUp(SignUp request);
}
