package br.com.reward.service;

import br.com.reward.entity.User;

public interface UserService {

	public User save(final User user) throws Throwable;

	public Iterable<User> findAll();

}
