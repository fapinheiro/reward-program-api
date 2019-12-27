package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.entity.PostalCode;
import br.com.reward.service.PostalCodeService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PostalCodeController {

    private final Logger LOG = LoggerFactory.getLogger(PostalCodeController.class);

    @Autowired
    private PostalCodeService service;

    @GetMapping(path = "/postal-codes")
	public Iterable<PostalCode> getAllByCodigoPostal(
            @RequestParam String code, 
            @RequestParam(required=false) Integer offset,
            @RequestParam(required=false) Integer limit) throws Throwable 
    {
        LOG.info("Listing all postal codes");
		return service.findAllByCodigoPostal(code, offset, limit);
	}

    @GetMapping(path = "/postal-codes/test")
	public Iterable<PostalCode> getAllByCodigoPostalTokens(
            @RequestParam String code, 
            @RequestParam(required=false) Integer offset,
            @RequestParam(required=false) Integer limit) throws Throwable 
    {
        LOG.info("Listing all postal codes");
		return service.findAllByCodigoPostal(code, offset, limit);
	}
}
