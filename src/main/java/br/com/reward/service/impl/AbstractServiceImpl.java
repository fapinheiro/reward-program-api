package br.com.reward.service.impl;

import static br.com.reward.util.Constant.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

public abstract class AbstractServiceImpl {

	@Value(value = "${reward.sql.max-records:24}")
	private Integer maxRecords;

	public AbstractServiceImpl() {}

	public Pageable getPageable(Integer limit, Integer offset) {

		if (StringUtils.isEmpty(offset)) {
			offset = ZERO_INT;
		}

		if (StringUtils.isEmpty(limit) || limit == ZERO_INT) {
			limit = maxRecords;
		}

		return PageRequest.of(offset, limit);
	}

	public Pageable getPageable(Integer limit, Integer offset, Sort sortedBy) {

		if (StringUtils.isEmpty(offset)) {
			offset = ZERO_INT;
		}

		if (StringUtils.isEmpty(limit) || limit == ZERO_INT) {
			limit = maxRecords;
		}

		return PageRequest.of(offset, limit, sortedBy);
	}
}
