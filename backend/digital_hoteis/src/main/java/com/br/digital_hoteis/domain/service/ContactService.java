package com.br.digital_hoteis.domain.service;

import com.br.digital_hoteis.domain.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;

public interface ContactService {
    Contact findContactById(UUID id);

    Page<Contact> findContact(Pageable page);

    Contact createContact(Contact contact);

    Contact updateContact(UUID id, Map<String, Object> fields);

    void deleteContactById(UUID id);
}
