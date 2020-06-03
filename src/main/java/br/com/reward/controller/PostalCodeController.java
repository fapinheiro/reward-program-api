package br.com.reward.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.entity.PostalCode;
import br.com.reward.service.PostalCodeService;
import br.com.reward.security.SecurityConstants;

@RestController
@RequestMapping(SecurityConstants.API_URL)
@CrossOrigin
public class PostalCodeController {

    private final Logger LOG = LoggerFactory.getLogger(PostalCodeController.class);

    @Autowired
    private PostalCodeService service;

    @GetMapping(path = "/postal-codes")
	public ResponseEntity<Page<PostalCode>> getAllByCodigoPostal(
            @RequestParam String code, 
            @RequestParam(required=false, defaultValue = "0") Integer offset,
            @RequestParam(required=false, defaultValue = "24") Integer limit) throws Throwable 
    {
        LOG.info("Listing all postal codes");
        return ResponseEntity.ok().body(
            service.findAllByPostalCodeWithPagination(code, offset, limit));
	}

    // @GetMapping(path = "/postal-codes/test")
	// public Iterable<PostalCode> getAllByCodigoPostalTokens(
    //         @RequestParam String code, 
    //         @RequestParam(required=false) Integer offset,
    //         @RequestParam(required=false) Integer limit) throws Throwable 
    // {
    //     LOG.info("Listing all postal codes");
	// 	return service.findAllByCodigoPostal(code, offset, limit);
	// }
}
