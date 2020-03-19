package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
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

import br.com.reward.entity.Indication;
import br.com.reward.service.IndicationService;

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
    public Iterable<Indication> getAllIndications(
        @RequestParam(required=false) Integer codClient,
        @RequestParam(required=false) String searchTerm,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime startCreationAt,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime endCreationAt,
        @RequestParam(required=false) Integer offset,
        @RequestParam(required=false) Integer limit) throws Throwable {

        if (!StringUtils.isEmpty(codClient) && 
            !StringUtils.isEmpty(searchTerm) && 
            !StringUtils.isEmpty(startCreationAt) &&
            !StringUtils.isEmpty(endCreationAt)) {

            return service.findByClient(codClient, searchTerm, startCreationAt, endCreationAt, offset, limit);

        } else if (!StringUtils.isEmpty(searchTerm)) {

            return service.findByClient(codClient, searchTerm, offset, limit);

        } else if ( !StringUtils.isEmpty(startCreationAt) && !StringUtils.isEmpty(endCreationAt)) { 

            return service.findByClient(codClient, startCreationAt, endCreationAt, offset, limit);

        } else if (!StringUtils.isEmpty(codClient)) {

            return service.findByClient(codClient, offset, limit);
        }

        return service.findAll(offset, limit);
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
        Indication ind = service.save(Indication);
        return ResponseEntity.ok().body(ind);
    }

    @PutMapping("/indications/{id}")
    public ResponseEntity<Indication> updateIndication(@Valid @RequestBody Indication newIndication, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating Indication of id %d", id));
        Indication ind = service.update(id, newIndication);
        return ResponseEntity.ok().body(ind);
    }

    @DeleteMapping("/indications/{id}")
    public ResponseEntity<Indication> deleteIndication(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting Indication of id %d", id));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
