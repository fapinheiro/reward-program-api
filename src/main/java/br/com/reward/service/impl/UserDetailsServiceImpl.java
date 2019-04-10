package br.com.reward.service.impl;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.reward.entity.User;
import br.com.reward.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User applicationUser = userRepository.findByLogin(login);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(login);
        }
        return new org.springframework.security.core.userdetails.User(
            applicationUser.getLogin(), applicationUser.getPassword(), Collections.emptyList());
    }

}
