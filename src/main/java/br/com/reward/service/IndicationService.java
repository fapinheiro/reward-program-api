package br.com.reward.service;

import java.time.OffsetDateTime;

import org.springframework.data.domain.Page;

import br.com.reward.entity.Indication;

public interface IndicationService {
	
	public Page<Indication> findByClient(
			Integer codClient, 
			String searchTerm,
			OffsetDateTime startCreationAt, OffsetDateTime endCreationAt, 
			Integer offset, Integer limit) throws Throwable;

	public Page<Indication> findByClient(
			Integer codClient, 
			String searchTerm,
			Integer offset, Integer limit) throws Throwable;

	public Page<Indication> findByClient(
			Integer codClient, 
			OffsetDateTime startCreationAt, OffsetDateTime endCreationAt, 
			Integer offset, Integer limit) throws Throwable;

	public Page<Indication> findByClient(
			Integer codClient, 
			Integer offset, Integer limit) throws Throwable;

	public Page<Indication> findAll(Integer offset, Integer limit);

	public Indication save(final Indication Indication) throws Throwable;

	public Indication findById(final Integer id) throws Throwable;

	public Indication update(final Integer id, final Indication newIndication) throws Throwable;

	public void delete(final Integer id) throws Throwable;
}
