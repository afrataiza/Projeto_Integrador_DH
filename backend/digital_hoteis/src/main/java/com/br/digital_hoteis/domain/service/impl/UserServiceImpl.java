package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.repository.UserRepository;
import com.br.digital_hoteis.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return (email) ->
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
