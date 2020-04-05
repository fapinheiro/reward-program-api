package br.com.reward.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.PostalCode;

@Repository
public interface PostalCodeRepository extends PagingAndSortingRepository<PostalCode, Integer> {
	// Like findByPlaceLike
	// StartingWith findByPlaceStartingWith
	// EndingWith findByPlaceEndingWith
    // Containing findByPlaceContaining
	Page<PostalCode> findAllByPostalCodeStartingWith(String postalCode, Pageable pageable);
}
