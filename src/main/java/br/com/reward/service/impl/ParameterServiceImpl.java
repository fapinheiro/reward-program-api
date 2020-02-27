package br.com.reward.service.impl;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.reward.entity.Parameter;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.ParameterRepository;
import br.com.reward.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterRepository dao;

	@Override
	public Iterable<Parameter> findAll() {
		return dao.findAll();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public Parameter update(final Integer id, final Parameter newParameter) throws Throwable {
		return dao.findById(id).map(param -> {
			param.setIndicationExpiration(newParameter.getIndicationExpiration());
			param.setScoreExpiration(newParameter.getScoreExpiration());
			param.setUpdatedAt(new Date());
			return dao.save(param);
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Parameter of id {%d} not found for updating", id));
		});
	}

}
