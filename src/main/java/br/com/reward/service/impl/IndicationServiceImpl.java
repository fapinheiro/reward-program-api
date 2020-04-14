package br.com.reward.service.impl;

import java.time.OffsetDateTime;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.reward.dto.IndicationDTO;
import br.com.reward.dto.IndicationUpdateDTO;
import br.com.reward.entity.Indication;
import br.com.reward.enums.IndicationStatusEnum;
import br.com.reward.enums.RolesEnum;
import br.com.reward.exception.AuthorizationException;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.IndicationRepository;
import br.com.reward.service.IndicationService;

@Service
public class IndicationServiceImpl extends AbstractServiceImpl implements IndicationService {

	@Autowired
	private IndicationRepository dao;

	@Override
	public Page<Indication> findAll(Integer offset, Integer limit) throws Throwable {
		if (!getHttpUtil().hasRequestRole(RolesEnum.ADMIN)) {
			throw new AuthorizationException("Operation not permited");
		}
		return dao.findAll(getPageable(limit, offset));
	}

	public Indication save(final Indication indication) throws Throwable {

		// Define default offsetdatetime
		if (indication.getCreationAt() == null) {
			indication.setStatus(IndicationStatusEnum.CREATED);
			indication.setCreationAt(OffsetDateTime.now(TimeZone.getDefault().toZoneId()));
		}

		return dao.save(indication);
	}

	public Indication findById(final Integer id) throws Throwable {
		Indication ind = dao.findById(id)
				.orElseThrow(() -> {
			throw new NotFoundException(String.format("A Indication of id {%d} not found for selecting", id));
		});
		checkPermition(ind.getClient().getClientId());
		return ind;
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public Indication update(final Integer id, final Indication newIndication) throws Throwable {
		return dao.findById(id).map(ind -> {
			checkPermition(ind.getClient().getClientId());
			if (newIndication.getEmail() != null) {
				ind.setEmail(newIndication.getEmail());
			}
			if (newIndication.getName() != null) {
				ind.setName(newIndication.getName());
			}
			if (newIndication.getPhone() != null) {
				ind.setPhone(newIndication.getPhone());
			}
			ind.setUpdatedAt(OffsetDateTime.now(TimeZone.getDefault().toZoneId()));
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
	public Page<Indication> findByClient(Integer clientId, String searchTerm, OffsetDateTime startCreationAt,
			OffsetDateTime endCreationAt, Integer offset, Integer limit) throws Throwable {

		checkPermition(clientId);
		if (StringUtils.isEmpty(clientId) && StringUtils.isEmpty(searchTerm) && StringUtils.isEmpty(startCreationAt)
				&& StringUtils.isEmpty(endCreationAt)) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(clientId, searchTerm, startCreationAt.toString(),
				endCreationAt.toString(), getPageable(limit, offset));
	}

	@Override
	public Page<Indication> findByClient(Integer clientId, String searchTerm, Integer offset, Integer limit)
			throws Throwable {

		checkPermition(clientId);
		if (StringUtils.isEmpty(clientId) && StringUtils.isEmpty(searchTerm)) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(clientId, searchTerm, getPageable(limit, offset));
	}

	@Override
	public Page<Indication> findByClient(Integer clientId, OffsetDateTime startCreationAt, OffsetDateTime endCreationAt,
			Integer offset, Integer limit) throws Throwable {
		
		checkPermition(clientId);
		if (StringUtils.isEmpty(clientId) && StringUtils.isEmpty(startCreationAt)
				&& StringUtils.isEmpty(endCreationAt)) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(clientId, startCreationAt.toString(), endCreationAt.toString(),
				getPageable(limit, offset));
	}

	@Override
	public Page<Indication> findByClient(Integer clientId, Integer offset, Integer limit) throws Throwable {

		checkPermition(clientId);
		if (StringUtils.isEmpty(clientId)) {
			throw new NotFoundException("Invalid parameters");
		}

		return dao.findByClientWithPagination(clientId, getPageable(limit, offset));
	}

	@Override
	public IndicationDTO saveDTO(IndicationDTO dto) throws Throwable {
		Indication indication = new Indication(dto);
		Indication newInd = this.save(indication);
		return new IndicationDTO(newInd);
	}

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public IndicationDTO updateDTO(Integer id, IndicationUpdateDTO newIndication) throws Throwable {
		Indication updatedInd = this.update(id, new Indication(id, newIndication));
		return new IndicationDTO(updatedInd);
	}

}
