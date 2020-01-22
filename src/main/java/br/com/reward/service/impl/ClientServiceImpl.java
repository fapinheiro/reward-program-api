package br.com.reward.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reward.entity.Client;
import br.com.reward.enums.IndicationStatusEnum;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.ClientRepository;
import br.com.reward.repository.IndicationRepository;
import br.com.reward.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository dao;

	@Autowired
	private IndicationRepository indDao;

	@Autowired
	private BCryptPasswordEncoder encoder;
	
    @Override
	public Iterable<Client> findAll(Integer offset, Integer limit) {

		if (offset == null) offset = 0;
		if (limit == null || limit == 0) limit = 3;

		Pageable pageable = PageRequest.of(offset, limit);

		return dao.findAll(pageable);
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public Client save(final Client client) throws Throwable {
		indDao.findByEmailAndStatus(client.getEmail()).ifPresent( ind -> { 
			ind.setStatus(IndicationStatusEnum.ACCEPTED);
			indDao.save(ind);
		});
		client.setPassword(encoder.encode(client.getPassword()));
		client.setCreationAt(new Date());
		return dao.save(client);
	}

	public Client findById(final Integer id) throws Throwable {
		return dao.findById(id).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Client of id {%d} not found for selecting", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public Client update(final Integer id, final Client newClient) throws Throwable {
        return dao.findById(id)
            .map(client -> {
                client.setEmail(newClient.getEmail());
                client.setName(newClient.getName());
                client.setNif(newClient.getNif());
                client.setPassword(encoder.encode(client.getPassword()));
                client.setPostalCode(newClient.getPostalCode());
                client.setUpdatedAt(new Date());
			    return dao.save(client);
		    }).orElseThrow(() -> {
			    throw new NotFoundException(String.format("A Client of id {%d} not found for updating", id));
		    });
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public void delete(final Integer id) throws Throwable {
        dao.findById(id)
        .map( client -> {
        	dao.deleteById(id);
            return client;
        })
        .orElseThrow(() -> {
		    throw new NotFoundException(String.format("A Client of id {%d} not found for deleting", id));
		});
	}
}
