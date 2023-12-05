package com.br.digital_hoteis.domain.exception;

import com.br.digital_hoteis.domain.agreggate.validation.Error;
import lombok.Getter;

import java.util.List;
@Getter
public class DomainException extends NoStacktraceException {

    protected final List<Error> errors;

    protected DomainException(final String aMessage, final List<Error> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(final Error anErrors) {
        return new DomainException(anErrors.error(), List.of(anErrors));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public boolean asErrors() {
        return this.errors != null && !this.errors.isEmpty();
    }

}