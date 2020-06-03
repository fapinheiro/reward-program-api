package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.reward.dto.ClientRequestDTO;
import br.com.reward.dto.ClientResponseDTO;
import br.com.reward.dto.ClientUpdateDTO;
import br.com.reward.entity.Client;
import br.com.reward.service.ClientService;
import br.com.reward.security.SecurityConstants;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping(SecurityConstants.API_URL)
@CrossOrigin
public class ClientController {

    private final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/clients")
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(
        @RequestParam(required=false, defaultValue = "0") Integer offset,
        @RequestParam(required=false, defaultValue = "24") Integer limit) throws Throwable {
        List<ClientResponseDTO> it = service.findAll(offset, limit)
            .stream()
            .map( client -> new ClientResponseDTO(client))
            .collect(Collectors.toList());
        return ResponseEntity.ok().body(it);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping(path = "/clients/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching client of id %d", id));
        Client client = service.findById(id);
        ClientResponseDTO dto = new ClientResponseDTO(client);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(path = "/clients")
    public ResponseEntity<ClientResponseDTO> addClient(@Valid @RequestBody ClientRequestDTO client) throws Throwable {
        LOG.info(String.format("Posting client of email %s", client.getEmail()));
        ClientResponseDTO newClient = service.saveDTO(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newClient.getClientId())
            .toUri();
        return ResponseEntity.created(uri).body(newClient);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PutMapping("/clients/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@Valid @RequestBody ClientUpdateDTO newClient, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating client of id %d", id));
        ClientResponseDTO updatedClient = service.updateDTO(id, newClient);
        return ResponseEntity.ok().body(updatedClient);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting client of id %d", id));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
