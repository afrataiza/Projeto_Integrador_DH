package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Host;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface HostService {
    Host findHostById(UUID id);
    Page<Host> findHostsByHotelId(UUID id, Pageable page);
    Page<Host> findAllHosts(Pageable page);
    Host createHost(Host host);
    Host updateHost(UUID id, Map<String, Object> fields);
    void deleteHostById(UUID id);
}
