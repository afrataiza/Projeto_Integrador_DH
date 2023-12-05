package com.br.digital_hoteis.app.api.controller;


import com.br.digital_hoteis.app.api.dto.SignIn;
import com.br.digital_hoteis.app.api.dto.SignUp;
import com.br.digital_hoteis.app.api.dto.request.AuthenticationSignInRequest;
import com.br.digital_hoteis.app.api.dto.request.AuthenticationSignUpRequest;
import com.br.digital_hoteis.app.api.dto.response.AuthenticationResponse;
import com.br.digital_hoteis.domain.entity.ConfirmationToken;
import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.repository.ConfirmationTokenRepository;
import com.br.digital_hoteis.domain.service.AuthenticationService;
import com.br.digital_hoteis.domain.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("v1/authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, ConfirmationTokenRepository confirmationTokenRepository, UserService userService) {
        this.authenticationService = authenticationService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userService = userService;
    }

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationSignInRequest request) {
        try {
            SignIn signIn = new SignIn(request.email(), request.password());
            String jwt = authenticationService.signIn(signIn);
            if (jwt != null) {
                return ResponseEntity.ok(new AuthenticationResponse(jwt));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to generate JWT.");
            }
        } catch (Exception e) {
            log.error("Error during sign-in:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to sign in. Please try again later.");
        }
    }

    @PostMapping(value = "/sign-up")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid AuthenticationSignUpRequest request) {
        boolean isHost = Boolean.TRUE.equals(request.isHost());

        SignUp signUp = SignUp.builder()
                .name(request.name())
                .surname(request.surname())
                .email(request.email())
                .retype_email(request.retype_email())
                .password(request.password())
                .isHost(isHost)
                .build();

        try {
            String confirmationToken = authenticationService.signUp(signUp);
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthenticationResponse(confirmationToken));
        } catch (IllegalArgumentException e) {
            log.error("Error during sign-up: Bad request - {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                log.error("Error during sign-up: Conflict - Email already in use. Email: {}", request.email(), e);
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Infelizmente não foi possível registrar. Por favor, tente novamente mais tarde");
            } else {
                log.error("Error during sign-up: HTTP Status - {}. Email: {}", e.getStatusCode(), request.email(), e);
                return ResponseEntity.status(e.getStatusCode()).body("Ocorreu um erro: " + e.getMessage());
            }
        } catch (Exception e) {
            log.error("Error during sign-up: Internal Server Error. Email: {}", request.email(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + e.getMessage());
        }
    }

//    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
//    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
//        return userService.confirmEmail(confirmationToken);
//    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            UserDetail user = token.getUserEntity();

            // Check if the user is not already enabled
            if (!user.isEnabled()) {
                user.setEnabled(true); // Enable the user after confirmation (you might have to set this based on your application logic)

                // Log in the user by generating a JWT
                String jwt = authenticationService.signIn(new SignIn(user.getEmail(), null), String.valueOf(token));


                // Optionally, you can redirect the user to the home page or include the JWT in the response
                return ResponseEntity.ok(new AuthenticationResponse(jwt));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already confirmed.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }


    @PostMapping("/log-out")
    public ResponseEntity<Void> logout() {
        try {
            SecurityContextHolder.clearContext();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }


}

