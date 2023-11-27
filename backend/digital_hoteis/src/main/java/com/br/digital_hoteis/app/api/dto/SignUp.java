package com.br.digital_hoteis.app.api.dto;

import com.br.digital_hoteis.domain.entity.UserPermissionEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUp {
    private String name;
    private String surname;
    private String email;
    private String retype_email;
    private String password;
    private UserPermissionEnum role;
}
