package com.br.digital_hoteis.domain.agreggate.validation;

@FunctionalInterface
public interface Validation<T> {
    T validate();
}
