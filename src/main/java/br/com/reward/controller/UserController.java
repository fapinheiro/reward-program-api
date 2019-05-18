package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.entity.User;
import br.com.reward.service.UserService;

import javax.validation.Valid;



@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users/sign-up")
    public User signUp(@Valid @RequestBody User user) throws Throwable {
        LOG.debug("user", user);
        return userService.save(user);
    }

    @GetMapping(path = "/users")
	public Iterable<User> getAllUsers() throws Throwable {
        LOG.info("Listing all users");
		return userService.findAll();
	}

}
