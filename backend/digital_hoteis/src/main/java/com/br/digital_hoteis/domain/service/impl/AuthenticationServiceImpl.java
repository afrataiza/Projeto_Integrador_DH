package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.app.api.dto.SignIn;
import com.br.digital_hoteis.app.api.dto.SignUp;
import com.br.digital_hoteis.app.api.dto.response.AuthenticationResponse;
import com.br.digital_hoteis.domain.entity.ConfirmationToken;
import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.repository.ConfirmationTokenRepository;
import com.br.digital_hoteis.domain.repository.UserRepository;
import com.br.digital_hoteis.domain.service.AuthenticationService;
import com.br.digital_hoteis.domain.service.EmailService;
import com.br.digital_hoteis.domain.service.JwtService;
import com.br.digital_hoteis.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private UserService userService;

    @Override
    public String signUp(SignUp request) {
        if (!request.getEmail().equals(request.getRetype_email())) {
            throw new IllegalArgumentException("Error: The email and retype_email must match.");
        }

        UserDetail user = UserDetail.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .isHost(request.isHost())
                .build();

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Erro: O e-mail já está em uso");
        }

        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Conclua seu cadastro");
        mailMessage.setText("Para confirmar sua conta, clique no link por favor: "
                + "<a href='http://localhost:9090/v1/authentication/confirm-account?token="
                + confirmationToken.getConfirmationToken() + "'>Clique aqui</a>");
        emailService.sendEmail(mailMessage);

        return confirmationToken.getConfirmationToken();
    }


//    @Override
//    public ResponseEntity<?> confirmUserAccount(String confirmationToken) {
//        return userService.confirmEmail(confirmationToken);
//    }

    @Override
    public ResponseEntity<?> confirmUserAccount(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            UserDetail user = token.getUserEntity();
            user.setEnabled(true); // Enable the user after confirmation (you might have to set this based on your application logic)

            // Log in the user by generating a JWT
            String jwt = jwtService.generateToken(user);

            // Optionally, you can redirect the user to the home page or include the JWT in the response
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
        }
    }


    @Override
    public String signIn(SignIn request, String token) {
        try {
            if (token != null) {
                // Authenticate based on token
                UserDetail user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
                // Add additional checks based on your application logic, if needed
                return jwtService.generateToken(user);
            } else {
                // Authenticate based on email and password
                var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
                authenticationManager.authenticate(authentication);
                var user = userRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
                return jwtService.generateToken(user);
            }
        } catch (Exception e) {
            logger.error("Error during sign-in:", e);
            throw e;
        }
    }


    @Override
    public String signIn(SignIn request) {
        try {
            var authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(authentication);
            var user = userRepository
                    .findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
            return jwtService.generateToken(user);
        } catch (Exception e) {
            logger.error("Error during sign-in:", e);
            throw e;
        }
    }

}


