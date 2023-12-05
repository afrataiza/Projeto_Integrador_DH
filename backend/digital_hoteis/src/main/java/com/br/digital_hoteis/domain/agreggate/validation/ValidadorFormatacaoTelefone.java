package com.br.digital_hoteis.domain.agreggate.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

public class ValidadorFormatacaoTelefone implements ConstraintValidator<FormatacaoTelefone, String> {
    private static final Logger logger = LoggerFactory.getLogger(ValidadorFormatacaoTelefone.class);

    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^\\(\\d{2}\\)\\s\\d{5}-\\d{4}$");


    @Override
    public boolean isValid(String telefone, ConstraintValidatorContext context) {
        if (telefone == null || TELEFONE_PATTERN.matcher(telefone).matches()) {
            return true;
        } else {
            // Log the invalid telefone value
            logger.debug("Invalid telefone value: {}", telefone);


            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("AAAAAAAAAAAA Formato de telefone inválido. Use o formato (xx) xxxxx-xxxx.")
                    .addConstraintViolation();
            return false;
        }
    }


    @Override
    public void initialize(FormatacaoTelefone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}

