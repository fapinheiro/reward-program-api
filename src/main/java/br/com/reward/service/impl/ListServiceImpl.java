package br.com.reward.service.impl;

import java.util.List;
import java.util.stream.*;

import org.springframework.stereotype.Service;

import br.com.reward.enums.IdentificationTypeEnum;
import br.com.reward.service.ListService;

@Service
public class ListServiceImpl implements ListService {

	@Override
	public List<IdentificationTypeEnum> findAllIdentificationType() {
		return Stream.of(IdentificationTypeEnum.values())
			.collect(Collectors.toList());
	}

}
