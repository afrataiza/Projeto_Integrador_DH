package com.br.digital_hoteis.app.api.dto.response;

public record ProblemResponse(
        int status,
        String detail,
        String message) {
}
