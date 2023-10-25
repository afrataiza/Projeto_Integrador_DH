package br.com.digital_hoteis.model.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String detail;
    public NotFoundException(String message) {
        super(message);
        this.detail = "resource not found";
    }
}
