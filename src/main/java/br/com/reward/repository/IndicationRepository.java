package br.com.reward.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.Indication;

@Repository
public interface IndicationRepository extends PagingAndSortingRepository<Indication, Integer> {

	// Like findByPlaceLike
	// StartingWith findByPlaceStartingWith
	// EndingWith findByPlaceEndingWith
	// Containing findByPlaceContaining

	@Query(
		value = "SELECT * FROM INDICATIONS S " +
				"WHERE S.COD_CLIENTE = ?1 " +
				"AND S.CREATION_AT BETWEEN ?2 AND ?3",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientAndCreationAtWithPagination(
			Integer codClient, 
			String startCreationAt, 
			String endCreationAt, 
			Pageable pageable);
	
	Page<Indication> findAll(Pageable pageable);
}
