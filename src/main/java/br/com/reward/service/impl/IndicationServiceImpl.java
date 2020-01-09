package br.com.reward.service.impl;

import java.time.OffsetDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.reward.entity.Indication;
import br.com.reward.enums.IndicationStatusEnum;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.IndicationRepository;
import br.com.reward.service.IndicationService;

@Service
public class IndicationServiceImpl implements IndicationService {

	@Value(value = "${reward.records.max-records:10}")
	private Integer maxRecords;

	@Autowired
	private IndicationRepository dao;

	@Override
	public Iterable<Indication> findAll(Integer offset, Integer limit) {
		return dao.findAll(getPagination(limit, offset));
	}

	public Indication save(final Indication indication) throws Throwable {

		// Define default offsetdatetime
		if (indication.getCreationAt() == null) {
			indication.setStatus(IndicationStatusEnum.CREATED);
			indication.setCreationAt(
				OffsetDateTime.now(
					TimeZone.getDefault().toZoneId()
				)
			);
		}

		return dao.save(indication);
	}

	public Indication findById(final Integer id) throws Throwable {
		return dao.findById(id).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Indication of id {%d} not found for selecting", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public Indication update(final Integer id, final Indication newIndication) throws Throwable {
		return dao.findById(id).map(ind -> {
			ind.setEmail(newIndication.getEmail());
			ind.setName(newIndication.getName());
			ind.setPhone(newIndication.getPhone());
			ind.setUpdatedAt(
				OffsetDateTime.now(
					TimeZone.getDefault().toZoneId()
				)
			);
			return dao.save(ind);
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Indication of id {%d} not found for updating", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public void delete(final Integer id) throws Throwable {
		dao.findById(id).map(ind -> {
			dao.deleteById(id);
			return ind;
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Indication of id {%d} not found for deleting", id));
		});
	}

	@Override
	public Iterable<Indication> findByClient(
			Integer codClient,
			String searchTerm,
			OffsetDateTime startCreationAt, OffsetDateTime endCreationAt, 
			Integer offset, Integer limit) throws Throwable {

		if (StringUtils.isEmpty(codClient) && StringUtils.isEmpty(searchTerm) && 
			StringUtils.isEmpty(startCreationAt) && StringUtils.isEmpty(endCreationAt) ) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(
			codClient, 
			searchTerm,
			startCreationAt.toString(), 
			endCreationAt.toString(), 
			getPagination(limit, offset));
	}

	@Override
	public Iterable<Indication> findByClient(
			Integer codClient,
			String searchTerm,
			Integer offset, Integer limit) throws Throwable {

		if (StringUtils.isEmpty(codClient) && 
			StringUtils.isEmpty(searchTerm)) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(
			codClient, 
			searchTerm,
			getPagination(limit, offset));
	}

	@Override
	public Iterable<Indication> findByClient(
			Integer codClient, 
			OffsetDateTime startCreationAt, OffsetDateTime endCreationAt, 
			Integer offset, Integer limit) throws Throwable {

		if (StringUtils.isEmpty(codClient) && 
			StringUtils.isEmpty(startCreationAt) &&
			StringUtils.isEmpty(endCreationAt) ) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(
			codClient, 
			startCreationAt.toString(), 
			endCreationAt.toString(), 
			getPagination(limit, offset));
	}

	@Override
	public Iterable<Indication> findByClient(
			Integer codClient, 
			Integer offset, Integer limit) throws Throwable {

		if (StringUtils.isEmpty(codClient) ) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(
			codClient,  
			getPagination(limit, offset));
	}

	private Pageable getPagination(Integer limit, Integer offset) {

		if (StringUtils.isEmpty(offset)) {
			offset = 0;
		}

		if (StringUtils.isEmpty(limit)) {
			limit = maxRecords;
		}

		return PageRequest.of(offset, limit);
	}
}
