package br.com.reward.controller;

import java.net.URI;
import java.time.OffsetDateTime;

import javax.validation.Valid;

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

import br.com.reward.dto.IndicationDTO;
import br.com.reward.dto.IndicationUpdateDTO;
import br.com.reward.entity.Indication;
import br.com.reward.service.IndicationService;
import br.com.reward.util.HTTPUtil;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class IndicationController {

    private final Logger LOG = LoggerFactory.getLogger(IndicationController.class);

    @Autowired
    private HTTPUtil httpUtil;
    
    @Autowired
    private IndicationService service;

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping(path = "/indications")
    public ResponseEntity<Page<IndicationDTO>> getAllIndications(
        @RequestParam(required=false) Integer clientId,
        @RequestParam(required=false) String searchTerm,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime startCreationAt,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime endCreationAt,
        @RequestParam(required=false, defaultValue = "0") Integer offset,
        @RequestParam(required=false, defaultValue = "24") Integer limit) throws Throwable {

        Page<Indication> list = null;
        if (!StringUtils.isEmpty(clientId) && 
            !StringUtils.isEmpty(searchTerm) && 
            !StringUtils.isEmpty(startCreationAt) &&
            !StringUtils.isEmpty(endCreationAt)) {

            list = service.findByClient(clientId, httpUtil.decodeParam(searchTerm), startCreationAt, endCreationAt, offset, limit);

        } else if (!StringUtils.isEmpty(searchTerm)) {

            list = service.findByClient(clientId, httpUtil.decodeParam(searchTerm), offset, limit);

        } else if ( !StringUtils.isEmpty(startCreationAt) && !StringUtils.isEmpty(endCreationAt)) { 

            list = service.findByClient(clientId, startCreationAt, endCreationAt, offset, limit);

        } else if (!StringUtils.isEmpty(clientId)) {

            list = service.findByClient(clientId, offset, limit);

        } else {

            list = service.findAll(offset, limit);
        }

        Page<IndicationDTO> dto = list.map( ind -> new IndicationDTO(ind));

        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping(path = "/indications/{id}")
    public ResponseEntity<IndicationDTO> getIndicationById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching Indication of id %d", id));
        IndicationDTO ind = new IndicationDTO(service.findById(id));
        return ResponseEntity.ok().body(ind);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PostMapping(path = "/indications")
    public ResponseEntity<IndicationDTO> addIndication(@Valid @RequestBody IndicationDTO dto) throws Throwable {
        LOG.info(String.format("Posting Indication of email %s", dto.getEmail()));
        IndicationDTO newInd = service.saveDTO(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newInd.getIndicationId())
            .toUri();
        return ResponseEntity.created(uri).body(newInd);
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @PutMapping("/indications/{id}")
    public ResponseEntity<IndicationDTO> updateIndication(@Valid @RequestBody IndicationUpdateDTO newIndication, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating Indication of id %d", id));
        IndicationDTO ind = service.updateDTO(id, newIndication);
        return ResponseEntity.ok().body(ind);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/indications/{id}")
    public ResponseEntity<Void> deleteIndication(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting Indication of id %d", id));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
