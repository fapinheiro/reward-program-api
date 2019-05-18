package br.com.reward.service;

import br.com.reward.entity.PostalCode;

public interface PostalCodeService {

	public Iterable<PostalCode> findAllByCodigoPostal(String codigoPostal, Integer offset, Integer limit);

}
