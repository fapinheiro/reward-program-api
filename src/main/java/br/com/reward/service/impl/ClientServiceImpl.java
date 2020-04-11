package br.com.reward.service.impl;

import java.util.Date;

// import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reward.entity.Client;
// import br.com.reward.exception.AuthorizationException;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.ClientRepository;
import br.com.reward.repository.IndicationRepository;
// import br.com.reward.security.JWTUtil;
import br.com.reward.service.ClientService;
// import br.com.reward.util.HTTPUtil;

@Service
public class ClientServiceImpl extends AbstractServiceImpl implements ClientService {
	
	// @Autowired
	// private HttpServletRequest request;

	@Autowired
	private ClientRepository clientDao;

	@Autowired
	private IndicationRepository indDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	// @Autowired
	// private HTTPUtil httpUtil;

	// @Autowired
	// private JWTUtil jwtUtil;
	
    @Override
	public Page<Client> findAll(Integer offset, Integer limit) {
		return clientDao.findAll(getPageable(limit, offset));
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public Client save(final Client client) throws Throwable {
		indDao.findByEmailAndStatus(client.getEmail()).ifPresent( ind -> { 
			// ind.setStatus(IndicationStatusEnum.ACCEPTED);
			indDao.save(ind);
		});
		client.setPassword(encoder.encode(client.getPassword()));
		client.setCreationAt(new Date());
		return clientDao.save(client);
	}

	public Client findById(final Integer id) throws Throwable {
		// checkClientId(id);
		return clientDao.findById(id).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Client of id {%d} not found for selecting", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public Client update(final Integer id, final Client newClient) throws Throwable {
		// checkClientId(id);
		return clientDao.findById(id)
            .map(client -> {
                client.setEmail(newClient.getEmail());
                client.setName(newClient.getName());
                // client.setNif(newClient.getNif());
                client.setPassword(encoder.encode(client.getPassword()));
                // client.setPostalCode(newClient.getPostalCode());
                client.setUpdatedAt(new Date());
			    return clientDao.save(client);
		    }).orElseThrow(() -> {
			    throw new NotFoundException(String.format("A Client of id {%d} not found for updating", id));
		    });
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public void delete(final Integer id) throws Throwable {
        clientDao.findById(id)
        .map( client -> {
        	clientDao.deleteById(id);
            return client;
        })
        .orElseThrow(() -> {
		    throw new NotFoundException(String.format("A Client of id {%d} not found for deleting", id));
		});
	}

	// private void checkClientId(Integer clientId) {
	// 	final String token = httpUtil.getRequestToken(request);
	// 	final Integer tokenClientId = jwtUtil.getClientId(token);
	// 	if (!clientId.equals(tokenClientId)) {
	// 		throw new AuthorizationException("Not authorized to execute operations in other clients");
	// 	}
	// }
}
