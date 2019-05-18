package br.com.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.reward.entity.PostalCode;
import br.com.reward.repository.PostalCodeRepository;
import br.com.reward.service.PostalCodeService;

@Service
public class PostalCodeServiceImpl implements PostalCodeService {

	@Autowired
	private PostalCodeRepository dao;

	@Override
	public Iterable<PostalCode> findAllByCodigoPostal(String codigoPostal, Integer offset, Integer limit) {

		if (offset == null) offset = 0;
		if (limit == null || limit == 0) limit = 3;

		Pageable sortedByLocalidadeAsc = PageRequest.of(offset, limit, Sort.by("localidade").ascending());

		return dao.findAllByCodigoPostal(codigoPostal, sortedByLocalidadeAsc);
	}

}
