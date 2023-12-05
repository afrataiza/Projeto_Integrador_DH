package com.br.digital_hoteis.domain.agreggate.events;

@FunctionalInterface
public interface DomainEventPublisher {

    void publishEvent(DomainEvent event);

}
