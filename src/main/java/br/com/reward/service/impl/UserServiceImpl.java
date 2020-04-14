package br.com.reward.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.reward.entity.User;
import br.com.reward.enums.RolesEnum;
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
		user.setActive(true);

		// Create roles
		List<RolesEnum> roles = new ArrayList<>();
		roles.add(RolesEnum.USER);
		user.setRoles( roles );

		return dao.save(user);
	}

	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

}
