package com.br.digital_hoteis.domain.service.impl;


import com.br.digital_hoteis.domain.service.JwtService;
import com.br.digital_hoteis.infraestructure.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String extractUserName(String token) {
        return jwtUtil.extractUserName(token);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return jwtUtil.validateToken(token, userDetails);
    }

}

