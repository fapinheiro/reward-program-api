package br.com.reward.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.reward.entity.User;
import br.com.reward.repository.UserRepository;
import br.com.reward.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository dao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User save(final User user) throws Throwable {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setCreationAt(new Date());
		return dao.save(user);
	}

	@Override
	public Iterable<User> findAll() {
		return dao.findAll();
	}

}
