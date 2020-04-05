package br.com.reward.service;


import org.springframework.data.domain.Page;

import br.com.reward.entity.PostalCode;

public interface PostalCodeService {

	public Page<PostalCode> findAllByPostalCodeWithPagination(String postalCode, Integer offset, Integer limit);

}
