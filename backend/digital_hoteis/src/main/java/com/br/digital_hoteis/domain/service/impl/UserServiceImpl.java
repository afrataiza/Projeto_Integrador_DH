package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.ConfirmationToken;
import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.entity.UserPermissionEnum;
import com.br.digital_hoteis.domain.exception.HostNotFoundException;
import com.br.digital_hoteis.domain.repository.ConfirmationTokenRepository;
import com.br.digital_hoteis.domain.repository.UserRepository;
import com.br.digital_hoteis.domain.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Override
    public UserDetailsService userDetailsService() {
        return (email) ->
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Nome de usuário não encontrado"));
    }

    @Override
    public ResponseEntity<?> saveUser(UserDetail user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        user.setRole(user.isHost() ? UserPermissionEnum.ADMIN : UserPermissionEnum.USER);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        String confirmationLink = "http://localhost:9090/confirm-account?token=" + confirmationToken.getConfirmationToken();


        String emailBody = "<p>Para confirmar a sua conta, por favor clique <a href=\"" + confirmationLink + "\">aqui</a>.</p>"
                + "<p>Alternativamente, você pode copiar e colar o seguinte link no seu navegador:<br>" + confirmationLink + "</p>"
                + "<p>Esse link é válido por 15 minutos.</p>";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText(emailBody);


        emailService.sendEmail(mailMessage);

        log.info("Confirmation Token generated: {}", confirmationToken.getConfirmationToken());

        return ResponseEntity.ok("Verify email by the link sent to your email address");
    }



//    @Override
//    public ResponseEntity<?> saveUser(UserDetail user) {
//        if (userRepository.existsByEmail(user.getEmail())) {
//            return ResponseEntity.badRequest().body("Error: Email is already in use!");
//        }
//        user.setRole(user.isHost() ? UserPermissionEnum.ADMIN : UserPermissionEnum.USER);
//        userRepository.save(user);
//
//        ConfirmationToken confirmationToken = new ConfirmationToken(user);
//        confirmationTokenRepository.save(confirmationToken);
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setText("To confirm your account, please click here : "
//                + "http://localhost:9090/confirm-account?token=" + confirmationToken.getConfirmationToken());
//        emailService.sendEmail(mailMessage);
//
//        log.info("Confirmation Token generated: {}", confirmationToken.getConfirmationToken());
//
//        return ResponseEntity.ok("Verify email by the link sent to your email address");
//    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        log.info("Received confirmation token: {}", confirmationToken);

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null && token.getUserEntity() != null) {
            UserDetail user = userRepository.findByEmailIgnoreCase(token.getUserEntity().getEmail());

            if (user != null) {
                user.setEnabled(true);

                try {
                    userRepository.save(user);
                    return ResponseEntity.ok("Email successfully verified!");
                } catch (Exception e) {
                    log.error("Error saving user after confirmation", e);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Internal error while verifying the email");
                }
            } else {
                log.error("User not found for confirmation token: {}", confirmationToken);
            }
        } else {
            log.error("Invalid confirmation token: {}", confirmationToken);
        }

        return ResponseEntity.badRequest().body("Error: Unable to verify the email");
    }




}

