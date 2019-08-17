package br.com.reward.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.Client;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<Client, Integer> {
	// Like findByPlaceLike
	// StartingWith findByPlaceStartingWith
	// EndingWith findByPlaceEndingWith
    // Containing findByPlaceContaining
	Page<Client> findAll(Pageable pageable);

	Client findByEmail(String email);
}
