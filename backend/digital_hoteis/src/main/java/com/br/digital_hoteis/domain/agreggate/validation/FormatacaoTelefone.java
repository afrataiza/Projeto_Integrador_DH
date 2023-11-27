package com.br.digital_hoteis.domain.agreggate.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidadorFormatacaoTelefone.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatacaoTelefone {
    String message() default "Telefone deve ter o formato de (dd) ddddd-dddd!!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
