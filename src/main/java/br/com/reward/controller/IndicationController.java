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

import br.com.reward.entity.Indication;
import br.com.reward.service.IndicationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class IndicationController {

    private final Logger LOG = LoggerFactory.getLogger(IndicationController.class);

    @Autowired
    private IndicationService service;

    @GetMapping(path = "/indications")
    public Iterable<Indication> getAllIndications(
        @RequestParam(required=false) Integer offset,
        @RequestParam(required=false) Integer limit) throws Throwable {
        return service.findAll(offset, limit);
    }

    @GetMapping(path = "/indications/{id}")
    public Indication getIndicationById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching Indication of id %d", id));
        return service.findById(id);
    }

    @PostMapping(path = "/indications")
    public Indication addIndication(@Valid @RequestBody Indication Indication) throws Throwable {
        LOG.info(String.format("Posting Indication of email %s", Indication.getEmail()));
        return service.save(Indication);
    }

    @PutMapping("/indications/{id}")
    public Indication updateIndication(@Valid @RequestBody Indication newIndication, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating Indication of id %d", id));
        return service.update(id, newIndication);
    }

    @DeleteMapping("/indications/{id}")
    public ResponseEntity<Indication> deleteIndication(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting Indication of id %d", id));
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
