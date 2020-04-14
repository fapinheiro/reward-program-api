package br.com.reward.service;

import java.util.List;

import br.com.reward.entity.User;

public interface UserService {

	public User save(final User user) throws Throwable;

	public List<User> findAll();

}
