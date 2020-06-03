package br.com.reward.service;

import java.util.List;

import br.com.reward.enums.IdentificationTypeEnum;

public interface ListService {
	public List<IdentificationTypeEnum> findAllIdentificationType();
}
