package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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

import br.com.reward.entity.Indication;
import br.com.reward.service.IndicationService;

import java.net.URI;
import java.time.OffsetDateTime;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class IndicationController {

    private final Logger LOG = LoggerFactory.getLogger(IndicationController.class);

    @Autowired
    private IndicationService service;


    @GetMapping(path = "/indications")
    public ResponseEntity<Page<Indication>> getAllIndications(
        @RequestParam(required=false) Integer codClient,
        @RequestParam(required=false) String searchTerm,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime startCreationAt,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime endCreationAt,
        @RequestParam(required=false, defaultValue = "0") Integer offset,
        @RequestParam(required=false, defaultValue = "24") Integer limit) throws Throwable {

        Page<Indication> list = null;
        if (!StringUtils.isEmpty(codClient) && 
            !StringUtils.isEmpty(searchTerm) && 
            !StringUtils.isEmpty(startCreationAt) &&
            !StringUtils.isEmpty(endCreationAt)) {

            list = service.findByClient(codClient, searchTerm, startCreationAt, endCreationAt, offset, limit);

        } else if (!StringUtils.isEmpty(searchTerm)) {

            list = service.findByClient(codClient, searchTerm, offset, limit);

        } else if ( !StringUtils.isEmpty(startCreationAt) && !StringUtils.isEmpty(endCreationAt)) { 

            list = service.findByClient(codClient, startCreationAt, endCreationAt, offset, limit);

        } else if (!StringUtils.isEmpty(codClient)) {

            list = service.findByClient(codClient, offset, limit);

        } else {

            list = service.findAll(offset, limit);
        }

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(path = "/indications/{id}")
    public ResponseEntity<Indication> getIndicationById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching Indication of id %d", id));
        Indication ind = service.findById(id);
        return ResponseEntity.ok().body(ind);
    }

    @PostMapping(path = "/indications")
    public ResponseEntity<Indication> addIndication(@Valid @RequestBody Indication Indication) throws Throwable {
        LOG.info(String.format("Posting Indication of email %s", Indication.getEmail()));
        Indication newInd = service.save(Indication);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newInd.getCodIndication())
            .toUri();
        return ResponseEntity.created(uri).body(newInd);
    }

    @PutMapping("/indications/{id}")
    public ResponseEntity<Indication> updateIndication(@Valid @RequestBody Indication newIndication, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating Indication of id %d", id));
        Indication ind = service.update(id, newIndication);
        return ResponseEntity.ok().body(ind);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/indications/{id}")
    public ResponseEntity<Indication> deleteIndication(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting Indication of id %d", id));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
