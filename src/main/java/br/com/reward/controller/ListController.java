package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.enums.IdentificationTypeEnum;
import br.com.reward.service.ListService;
import br.com.reward.security.SecurityConstants;

import java.util.List;


@RestController
@RequestMapping(SecurityConstants.API_URL)
@CrossOrigin
public class ListController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ListService listService;

    @GetMapping(path = "/identifications")
	public ResponseEntity<Iterable<IdentificationTypeEnum>> getAllIdentificationsType() throws Throwable {
        LOG.info("Listing all identifications");
        List<IdentificationTypeEnum> types =  listService.findAllIdentificationType();
        return ResponseEntity.ok().body(types);
	}

}
