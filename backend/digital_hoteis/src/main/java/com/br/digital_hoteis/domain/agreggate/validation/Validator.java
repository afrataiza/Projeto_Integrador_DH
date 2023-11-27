package com.br.digital_hoteis.domain.agreggate.validation;

public abstract class Validator {

    private final ValidationHandler handler;

    protected Validator(ValidationHandler handler) {
        this.handler = handler;
    }

    public abstract void validate();

    protected ValidationHandler handler() {
        return this.handler;
    }
}
