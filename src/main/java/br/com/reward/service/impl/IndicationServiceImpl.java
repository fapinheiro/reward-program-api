package br.com.reward.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reward.entity.Indication;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.IndicationRepository;
import br.com.reward.service.IndicationService;

@Service
public class IndicationServiceImpl implements IndicationService {

	@Autowired
	private IndicationRepository dao;

    @Override
	public Iterable<Indication> findAll(Integer offset, Integer limit) {

		if (offset == null) offset = 0;
		if (limit == null || limit == 0) limit = 3;

		Pageable pageable = PageRequest.of(offset, limit);

		return dao.findAll(pageable);
	}

	public Indication save(final Indication Indication) throws Throwable {
		Indication.setCreationAt(new Date());
		return dao.save(Indication);
	}

	public Indication findById(final Integer id) throws Throwable {
		return dao.findById(id).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Indication of id {%d} not found for selecting", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public Indication update(final Integer id, final Indication newIndication) throws Throwable {
        return dao.findById(id)
            .map( ind -> {
                ind.setEmail(newIndication.getEmail());
                ind.setName(newIndication.getName());
                ind.setPhone(newIndication.getPhone());
                ind.setUpdatedAt(new Date());
			    return dao.save(ind);
		    }).orElseThrow(() -> {
			    throw new NotFoundException(String.format("A Indication of id {%d} not found for updating", id));
		    });
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout=5)
	public void delete(final Integer id) throws Throwable {
        dao.findById(id)
        .map( 
            ind -> {
                dao.deleteById(id);
                return ind;
			}
		).orElseThrow(() -> {
		    throw new NotFoundException(String.format("A Indication of id {%d} not found for deleting", id));
		});
	}
}
