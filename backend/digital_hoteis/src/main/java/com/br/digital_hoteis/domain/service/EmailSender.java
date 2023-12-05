package com.br.digital_hoteis.domain.service;

public interface EmailSender {
    void sendEmail(String to, String email) throws Exception;
}
