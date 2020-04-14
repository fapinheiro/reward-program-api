package br.com.reward.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reward.dto.ClientRequestDTO;
import br.com.reward.dto.ClientResponseDTO;
import br.com.reward.dto.ClientUpdateDTO;
import br.com.reward.entity.Account;
import br.com.reward.entity.Address;
import br.com.reward.entity.Client;
import br.com.reward.enums.RolesEnum;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.ClientRepository;
// import br.com.reward.repository.IndicationRepository;
import br.com.reward.service.ClientService;

@Service
public class ClientServiceImpl extends AbstractServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientDao;

	// @Autowired
	// private IndicationRepository indDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public Page<Client> findAll(Integer offset, Integer limit) {
		return clientDao.findAll(getPageable(limit, offset));
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public Client save(final Client client) throws Throwable {
		// indDao.findByEmailAndStatus(client.getEmail())
		// .ifPresent( ind -> {
		// // ind.setStatus(IndicationStatusEnum.ACCEPTED);
		// indDao.save(ind);
		// });

		final Date creationAt = new Date();

		// Define client attributes
		client.setPassword(encoder.encode(client.getPassword()));
		client.setCreationAt(creationAt);
		client.setActive(true);

		// Create address
		Address address = client.getAddress();
		address.setCreationAt(creationAt);
		address.setClient(client);

		// Create account
		Account account = new Account();
		account.setCreationAt(creationAt);
		account.setClient(client);
		account.setScoreBalance(0);
		client.setAccount(account);

		// Create identifications
		client.getIdentifications().forEach(identification -> {
			identification.setClient(client);
			identification.setCreationAt(creationAt);
		});

		// Create contacts
		client.getContacts().forEach(contact -> {
			contact.setClient(client);
			contact.setCreationAt(creationAt);
		});

		// Create roles
		List<RolesEnum> roles = new ArrayList<>();
		roles.add(RolesEnum.CLIENT);
		client.setRoles(roles);

		return clientDao.save(client);
	}

	public Client findById(final Integer id) throws Throwable {
		checkPermition(id);
		return clientDao.findById(id).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Client of id {%d} not found for selecting", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public Client update(final Integer id, final Client newClient) throws Throwable {
		checkPermition(id);
		return clientDao.findById(id).map(client -> {

			if (newClient.getEmail() != null) {
				client.setEmail(newClient.getEmail());
			}

			if (newClient.getName() != null) {
				client.setName(newClient.getName());
			}
			
			// client.setNif(newClient.getNif());
			// client.setPassword(encoder.encode(client.getPassword()));
			// client.setPostalCode(newClient.getPostalCode());
			client.setUpdatedAt(new Date());
			return clientDao.save(client);
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Client of id {%d} not found for updating", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public void delete(final Integer id) throws Throwable {
		clientDao.findById(id).map(client -> {
			clientDao.deleteById(id);
			return client;
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Client of id {%d} not found for deleting", id));
		});
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public ClientResponseDTO saveDTO(ClientRequestDTO clientDTO) throws Throwable {
		Client request = new Client(clientDTO);
		Client response = this.save(request);
		return new ClientResponseDTO(response);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public ClientResponseDTO updateDTO(Integer id, @Valid ClientUpdateDTO newClient) throws Throwable {
		Client request = new Client(newClient);
		Client response = this.update(id, request);
		return new ClientResponseDTO(response);
	}

}
