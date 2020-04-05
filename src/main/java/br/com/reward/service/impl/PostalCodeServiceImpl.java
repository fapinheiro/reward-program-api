package br.com.reward.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.reward.entity.PostalCode;
import br.com.reward.repository.PostalCodeRepository;
import br.com.reward.service.PostalCodeService;

@Service
public class PostalCodeServiceImpl extends AbstractServiceImpl implements PostalCodeService {

	@Autowired
	private PostalCodeRepository dao;

	@Override
	public Page<PostalCode> findAllByPostalCodeWithPagination(String postalCode, Integer offset, Integer limit) {
		Pageable sortedByLocalidadeAsc = getPageable(limit, offset, Sort.by("locale").ascending());
		return dao.findAllByPostalCodeStartingWith(postalCode, sortedByLocalidadeAsc);
	}

}
