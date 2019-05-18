package br.com.reward.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.PostalCode;

@Repository
public interface PostalCodeRepository extends PagingAndSortingRepository<PostalCode, Integer> {
	List<PostalCode> findAllByCodigoPostal(String codigoPostal, Pageable pageable);
}
