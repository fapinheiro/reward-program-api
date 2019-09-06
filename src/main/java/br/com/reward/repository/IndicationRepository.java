package br.com.reward.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.Indication;

@Repository
public interface IndicationRepository extends PagingAndSortingRepository<Indication, Integer> {
	// Like findByPlaceLike
	// StartingWith findByPlaceStartingWith
	// EndingWith findByPlaceEndingWith
    // Containing findByPlaceContaining
	Page<Indication> findAll(Pageable pageable);
}
