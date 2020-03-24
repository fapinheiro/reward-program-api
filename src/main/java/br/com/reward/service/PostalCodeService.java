package br.com.reward.service;


import org.springframework.data.domain.Page;

import br.com.reward.entity.PostalCode;

public interface PostalCodeService {

	public Page<PostalCode> findAllByCodigoPostal(String codigoPostal, Integer offset, Integer limit);

}
