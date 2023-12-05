package com.br.digital_hoteis.domain.agreggate.validation;

import org.springframework.stereotype.Service;
import java.util.function.Predicate;
@Service
public class EmailValidator implements Predicate<String> {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Override
    public boolean test(String email) {
        return email.matches(EMAIL_REGEX);
    }
}

