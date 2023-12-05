package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Host;
import com.br.digital_hoteis.domain.entity.UserDetail;
import com.br.digital_hoteis.domain.entity.UserPermissionEnum;
import com.br.digital_hoteis.domain.exception.HostNotFoundException;
import com.br.digital_hoteis.domain.repository.HostRepository;
import com.br.digital_hoteis.domain.repository.UserRepository;
import com.br.digital_hoteis.domain.repository.specification.HostSpecification;
import com.br.digital_hoteis.domain.service.HostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.UUID;

import static org.springframework.util.ReflectionUtils.findField;
import static org.springframework.util.ReflectionUtils.getField;


@Slf4j
@Service
@AllArgsConstructor
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    @Override
    public Host findHostById(UUID id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));
    }

    @Override
    public Page<Host> findHostsByHotelId(UUID id, Pageable page) {
        return hostRepository.findAll(HostSpecification.byHotelId(id), page);
    }

    @Override
    public Page<Host> findAllHosts(Pageable page) {
        return hostRepository.findAll(page);
    }

    @Override
    public Host createHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Host updateHost(UUID id, Map<String, Object> fields) {
        Host host = hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));
        Host input = mapper.convertValue(fields, Host.class);
        fields.forEach((property, value) -> {
            Field field = findField(Host.class, property);
            if (field == null) {
                log.error("Field not found on the payload: '{}', ignoring it.", property);
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, host, newValue);
        });
        return hostRepository.save(host);
    }

    @Override
    public void deleteHostById(UUID id) {
        Host host = hostRepository.findById(id)
                .orElseThrow(() -> new HostNotFoundException(id));
        hostRepository.delete(host);
    }

    @Override
    public void manageUserRoles(UUID userId, UserPermissionEnum newRole) {
        UserDetail user = userRepository.findById(userId)
                .orElseThrow(() -> new HostNotFoundException(userId));
        user.setRole(newRole);
        userRepository.save(user);
    }

}