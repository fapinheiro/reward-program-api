package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.reward.dto.UserDTO;
import br.com.reward.entity.User;
import br.com.reward.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users/sign-up")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody User user) throws Throwable {
        LOG.info("user", user);
        User newUser = userService.save(user);
        UserDTO dto = new UserDTO(newUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/users/{id}")
                        .buildAndExpand(dto.getId())
                        .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDTO> getClientById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching user of id %d", id));
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = "/users")
	public ResponseEntity<Iterable<UserDTO>>  getAllUsers() throws Throwable {
        LOG.info("Listing all users");
        List<UserDTO> it =  userService.findAll()
                                .stream()
                                .map( user -> new UserDTO(user))
                                .collect(Collectors.toList());
        return ResponseEntity.ok().body(it);
	}

}
