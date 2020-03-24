package br.com.reward.service;


import org.springframework.data.domain.Page;

import br.com.reward.entity.Client;

public interface ClientService {
	
	public Page<Client> findAll(Integer offset, Integer limit);

	public Client save(final Client client) throws Throwable;

	public Client findById(final Integer id) throws Throwable;

	public Client update(final Integer id, final Client newClient) throws Throwable;

	public void delete(final Integer id) throws Throwable;
}
