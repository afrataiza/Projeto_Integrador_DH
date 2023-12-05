package com.br.digital_hoteis.infraestructure.jpa;

import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.repository.UserRepository;
import com.br.digital_hoteis.domain.utils.InstantUtils;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@AllArgsConstructor
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditingDateTimeProvider")
public class JpaAuditingConfiguration {
    private final UserRepository userRepository;
    @Bean
    public DateTimeProvider auditingDateTimeProvider() {
        return () -> Optional.of(InstantUtils.now());
    }

    @Bean
    public AuditorAware<UserDetail> auditorAware() {
        return Optional::empty;
    }

}
