package br.com.reward.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.dto.ParameterDTO;
import br.com.reward.service.ParameterService;
import br.com.reward.security.SecurityConstants;

@RestController
@RequestMapping(SecurityConstants.API_URL)
@CrossOrigin
public class ParameterController {

    private final Logger LOG = LoggerFactory.getLogger(ParameterController.class);

    @Autowired
    private ParameterService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/parameters")
	public ResponseEntity<List<ParameterDTO>> getAllParameters() throws Throwable {
        return ResponseEntity.ok().body(
            service.findAll()
                .stream()
                .map( obj -> new ParameterDTO(obj))
                .collect(Collectors.toList()));
	}

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/parameters/{id}")
    public ResponseEntity<ParameterDTO> updateParameter(@Valid @RequestBody ParameterDTO newParameter, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating parameter of id %d", id));
        ParameterDTO param = service.updateDTO(id, newParameter);
        return ResponseEntity.ok().body(param);
    }

}
