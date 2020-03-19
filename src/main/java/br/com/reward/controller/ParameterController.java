package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.entity.Parameter;
import br.com.reward.service.ParameterService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class ParameterController {

    private final Logger LOG = LoggerFactory.getLogger(ParameterController.class);

    @Autowired
    private ParameterService service;

    @GetMapping(path = "/parameters")
	public Iterable<Parameter> getAllParameters() throws Throwable {
		return service.findAll();
	}

    @PutMapping("/parameters/{id}")
    public ResponseEntity<Parameter> updateParameter(@Valid @RequestBody Parameter newParameter, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating parameter of id %d", id));
        Parameter param = service.update(id, newParameter);
        return ResponseEntity.ok().body(param);
    }

}
