package com.br.digital_hoteis.app.api.dto.request;

public record CreateUserRequest(
                                String name,
                                String surname,
                                String email,
                                String retype_email,
                                String password) {

}
