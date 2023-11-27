package com.br.digital_hoteis.domain.service.impl;

import com.br.digital_hoteis.domain.entity.Contact;
import com.br.digital_hoteis.domain.exception.ContactNotFoundException;
import com.br.digital_hoteis.domain.repository.ContactRepository;
import com.br.digital_hoteis.domain.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
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
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private final ObjectMapper mapper;

    @Override
    public Contact findContactById(UUID id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
    }

    @Override
    public Page<Contact> findContact(Pageable page) {
        return contactRepository.findAll(page);
    }


    @Override
    public Contact createContact(Contact contact) {
        try {
            return contactRepository.save(contact);
        } catch (ConstraintViolationException e) {
            log.error("A Validation error occurred while creating a contact: {}", e.getMessage());
            throw e; // Você pode relançar a exceção se desejar que ela seja tratada em outro lugar também.
        }
    }

    @Override
    public Contact updateContact(UUID id, Map<String, Object> fields) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        Contact input = mapper.convertValue(fields, Contact.class);
        fields.forEach((property, value) -> {
            Field field = findField(Contact.class, property);
            if (field == null) {
                log.error("field not found on the payload! '%s', I'll ignore it.".formatted(property));
                return;
            }
            field.setAccessible(true);
            Object newValue = getField(field, input);
            ReflectionUtils.setField(field, contact, newValue);
        });
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(UUID id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        contactRepository.delete(contact);
    }
}

