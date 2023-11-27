package com.br.digital_hoteis.infraestructure.jpa;

import com.br.digital_hoteis.domain.entity.UserDetail;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuditorAware implements AuditorAware<UserDetail> {

    @Override
    public Optional<UserDetail> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetail) {
            UserDetail currentUser = (UserDetail) authentication.getPrincipal();
            return Optional.of(currentUser);
        }


        return Optional.empty();
    }
}
