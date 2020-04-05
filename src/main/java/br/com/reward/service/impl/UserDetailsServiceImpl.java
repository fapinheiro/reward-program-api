package br.com.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.reward.entity.Client;
import br.com.reward.entity.User;
import br.com.reward.repository.ClientRepository;
import br.com.reward.repository.UserRepository;
import br.com.reward.security.MyUserDetails;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
	private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;
    
    // public UserDetailsServiceImpl(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(login);
        if (client == null) {
            User user = userRepository.findByLogin(login);
            if (user == null) {
                throw new UsernameNotFoundException(login);
            }
            return  new MyUserDetails(user.getLogin(), user.getPassword());    
        }
        return  new MyUserDetails(client.getClientId(), client.getEmail(), client.getPassword());
    }

}
