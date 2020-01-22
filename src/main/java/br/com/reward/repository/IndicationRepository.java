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
				"WHERE S.COD_CLIENTE = ?1 AND S.COD_CLIENTE = T.COD_CLIENTE " +
				"AND (S.EMAIL LIKE %?2% OR S.NAME LIKE %?2%) " +
				"AND S.CREATION_AT BETWEEN ?3 AND ?4",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer codClient,
			String searchTerm, 
			String startCreationAt, 
			String endCreationAt, 
			Pageable pageable);

	@Query(
		value = "SELECT * FROM INDICATIONS S, CLIENTS T " +
				"WHERE S.COD_CLIENTE = ?1 AND S.COD_CLIENTE = T.COD_CLIENTE " +
				"AND (S.EMAIL LIKE %?2% OR S.NAME LIKE %?2%) ",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer codClient,
			String searchTerm, 
			Pageable pageable);

	@Query(
		value = "SELECT * FROM INDICATIONS S, CLIENTS T " +
				"WHERE S.COD_CLIENTE = ?1 AND S.COD_CLIENTE = T.COD_CLIENTE " +
				"AND S.CREATION_AT BETWEEN ?2 AND ?3",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer codClient, 
			String startCreationAt, 
			String endCreationAt, 
			Pageable pageable);
	
	@Query(
		value = "SELECT * FROM INDICATIONS S " +
				"WHERE S.COD_CLIENTE = ?1 ",
		countQuery = "SELECT COUNT(*) FROM INDICATIONS",
		nativeQuery = true
	)
	Page<Indication> findByClientWithPagination(
			Integer codClient, 
			Pageable pageable);

	Page<Indication> findAll(Pageable pageable);

	@Query(
		value = "SELECT * FROM INDICATIONS S " +
				"WHERE S.EMAIL = ?1 AND (" +
					"S.STATUS = 'CREATED' OR " +
					"S.STATUS = 'SENT' OR " +
					"S.STATUS = 'RESENT')",
		nativeQuery = true
	)
	Optional<Indication> findByEmailAndStatus(String email);
}
