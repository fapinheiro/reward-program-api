package br.com.reward.repository;


import java.util.Optional;

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
		value = "SELECT * FROM INDICATIONS S, CLIENTS T " +
				"WHERE S.CLIENT_ID = ?1 AND S.CLIENT_ID = T.CLIENT_ID " +
				"AND (S.EMAIL LIKE %?2% OR S.NAME LIKE %?2%) " +
				"AND S.CREATION_AT BETWEEN ?3 AND ?4",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer clientId,
			String searchTerm, 
			String startCreationAt, 
			String endCreationAt, 
			Pageable pageable);

	@Query(
		value = "SELECT * FROM INDICATIONS S, CLIENTS T " +
				"WHERE S.CLIENT_ID = ?1 AND S.CLIENT_ID = T.CLIENT_ID " +
				"AND (S.EMAIL LIKE %?2% OR S.NAME LIKE %?2%) ",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer clientId,
			String searchTerm, 
			Pageable pageable);

	@Query(
		value = "SELECT * FROM INDICATIONS S, CLIENTS T " +
				"WHERE S.CLIENT_ID = ?1 AND S.CLIENT_ID = T.CLIENT_ID " +
				"AND S.CREATION_AT BETWEEN ?2 AND ?3",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer clientId, 
			String startCreationAt, 
			String endCreationAt, 
			Pageable pageable);
	
	@Query(
		value = "SELECT * FROM INDICATIONS S " +
				"WHERE S.CLIENT_ID = ?1 ",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer clientId, 
			Pageable pageable);

	Page<Indication> findAll(Pageable pageable);

	@Query(
		value = "SELECT * FROM INDICATIONS S " +
				"WHERE S.EMAIL = ?1 AND " +
					"S.STATUS in (1,2,3)", // Only created, sent or resent 
		nativeQuery = true
	)
	Optional<Indication> findByEmailAndStatus(String email);
}
