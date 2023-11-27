package com.br.digital_hoteis.app.api.controller;

import com.br.digital_hoteis.app.api.HostApi;
import com.br.digital_hoteis.app.api.dto.request.CreateContactRequest;
import com.br.digital_hoteis.app.api.dto.request.CreateHostRequest;
import com.br.digital_hoteis.app.api.dto.response.ContactSummaryResponse;
import com.br.digital_hoteis.app.api.dto.response.HostDetailedResponse;
import com.br.digital_hoteis.app.api.dto.response.HostSummaryResponse;
import com.br.digital_hoteis.app.api.dto.response.HotelSummaryResponse;
import com.br.digital_hoteis.domain.entity.Contact;
import com.br.digital_hoteis.domain.entity.Host;
import com.br.digital_hoteis.domain.service.ContactService;
import com.br.digital_hoteis.domain.service.HostService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class HostController implements HostApi {

    private final HostService hostService;
    private final ContactService contactService;

    @Override
    public ResponseEntity<Page<HostSummaryResponse>> findAllHosts(Pageable page) {
        Page<Host> pageHost = hostService.findAllHosts(page);
        Page<HostSummaryResponse> response = pageHost
                .map(host -> new HostSummaryResponse(host.getId(), host.getName(), host.getSurname()));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<HostDetailedResponse> findHostById(UUID hostId) {
        Host host = hostService.findHostById(hostId);

        ContactSummaryResponse contact = new ContactSummaryResponse(
                host.getContact().getId(),
                host.getContact().getEmail(),
                host.getContact().getPhone_number()
        );

        HotelSummaryResponse hotel = new HotelSummaryResponse(
                host.getHotel().getId(),
                host.getHotel().getTrading_name(),
                host.getHotel().getCnpj(),
                host.getHotel().getDescription()
        );

        HostDetailedResponse response = new HostDetailedResponse(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getBirthdate(),
                host.getGender(),
                contact,
                hotel
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> createHost(CreateHostRequest request, CreateContactRequest contactRequest) {

        Contact contact = new Contact();
        contact.setEmail(contactRequest.email());
        contact.setPhone_number(contactRequest.phone_number());

        Host host = Host.newHost(
                request.name(),
                request.surname(),
                request.birthdate(),
                request.gender(),
                contact
        );

        Host createdHost = hostService.createHost(host);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(createdHost.getId()).toUri()).build();
    }

    @Override
    public ResponseEntity<Void> updateHostById(UUID hostId, Map<String, Object> fields) {
        hostService.updateHost(hostId,fields);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> deleteHostById(UUID hostId) {
        hostService.deleteHostById(hostId);
        return ResponseEntity.noContent().build();
    }
}
