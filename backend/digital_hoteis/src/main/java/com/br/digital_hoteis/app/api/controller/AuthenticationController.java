package com.br.digital_hoteis.app.api.controller;


import com.br.digital_hoteis.app.api.dto.request.AuthenticationSignInRequest;
import com.br.digital_hoteis.app.api.dto.request.AuthenticationSignUpRequest;
import com.br.digital_hoteis.app.api.dto.response.AuthenticationResponse;
import com.br.digital_hoteis.app.api.dto.SignIn;
import com.br.digital_hoteis.app.api.dto.SignUp;
import com.br.digital_hoteis.domain.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationSignInRequest request) {
        try {
            SignIn signIn = new SignIn(request.email(), request.password());
            String jwt = authenticationService.signIn(signIn);
            if (jwt != null) {
                return ResponseEntity.ok(new AuthenticationResponse(jwt));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The JWT is null.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }


    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationSignUpRequest request) throws Exception{
        SignUp signUp = SignUp.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .email(request.retype_email())
                .password(request.password())
                .role(request.role())
                .build();
        String jwt = authenticationService.signUp(signUp);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(jwt));
    }
}
