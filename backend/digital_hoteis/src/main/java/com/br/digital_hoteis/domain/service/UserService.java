package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.entity.UserPermissionEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService {

    UserDetailsService userDetailsService();

    ResponseEntity<?> saveUser(UserDetail user);

    ResponseEntity<?> confirmEmail(String confirmationToken);

}
