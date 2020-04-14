package br.com.reward.service;


import javax.validation.Valid;

import org.springframework.data.domain.Page;

import br.com.reward.dto.ClientRequestDTO;
import br.com.reward.dto.ClientResponseDTO;
import br.com.reward.dto.ClientUpdateDTO;
import br.com.reward.entity.Client;

public interface ClientService {
	
	public Page<Client> findAll(Integer offset, Integer limit);

	public ClientResponseDTO saveDTO(final ClientRequestDTO client) throws Throwable;

	public Client save(final Client client) throws Throwable;

	public Client findById(final Integer id) throws Throwable;

	public Client update(final Integer id, final Client newClient) throws Throwable;

	public void delete(final Integer id) throws Throwable;

	public ClientResponseDTO updateDTO(Integer id, @Valid ClientUpdateDTO newClient) throws Throwable;;
}
