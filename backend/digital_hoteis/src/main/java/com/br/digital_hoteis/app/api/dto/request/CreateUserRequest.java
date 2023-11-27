package com.br.digital_hoteis.app.api.dto.request;

import com.br.digital_hoteis.domain.entity.UserPermissionEnum;


public record CreateUserRequest(
                                String name,
                                String surname,
                                String email,
                                String retype_email,
                                String password,
                                UserPermissionEnum role) {

}
