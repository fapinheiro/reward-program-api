package br.com.reward.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.reward.entity.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Integer> {
	// Like findByPlaceLike
	// StartingWith findByPlaceStartingWith
	// EndingWith findByPlaceEndingWith
	// Containing findByPlaceContaining
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	Page<Client> findAll(Pageable pageable);

	@Transactional(readOnly = true)
	Client findByEmail(String email);
}
