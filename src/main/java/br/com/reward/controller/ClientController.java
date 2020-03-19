package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.com.reward.entity.Client;
import br.com.reward.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ClientController {

    private final Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService service;

    @GetMapping(path = "/clients")
    public Iterable<Client> getAllClients(
        @RequestParam(required=false) Integer offset,
        @RequestParam(required=false) Integer limit) throws Throwable {
        return service.findAll(offset, limit);
    }

    @GetMapping(path = "/clients/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching client of id %d", id));
        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping(path = "/clients")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) throws Throwable {
        LOG.info(String.format("Posting client of email %s", client.getEmail()));
        Client clientDao = service.save(client);
        return ResponseEntity.ok().body(clientDao);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<Client> updateClient(@Valid @RequestBody Client newClient, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating client of id %d", id));
        Client clientDao = service.update(id, newClient);
        return ResponseEntity.ok().body(clientDao);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting client of id %d", id));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
