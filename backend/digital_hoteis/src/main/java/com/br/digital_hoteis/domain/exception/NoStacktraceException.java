package com.br.digital_hoteis.domain.exception;

public class NoStacktraceException extends RuntimeException {

    public NoStacktraceException(final String message) {
        this(message, null);
    }

    public NoStacktraceException(final String message, final Throwable cause) {
        super(message, cause, true, false);
    }
}
