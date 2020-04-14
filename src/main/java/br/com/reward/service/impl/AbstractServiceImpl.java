package br.com.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

import br.com.reward.enums.RolesEnum;
import br.com.reward.exception.AuthorizationException;
import br.com.reward.util.HTTPUtil;
import br.com.reward.util.JWTUtil;

// @Component
public abstract class AbstractServiceImpl {

	public static final Integer ZERO_INT = 0;

	@Value(value = "${reward.sql.max-records:24}")
	private Integer maxRecords;

	@Autowired
	private HTTPUtil httpUtil;

	@Autowired
	private JWTUtil jwtUtil;
	
	public AbstractServiceImpl() {}


	public HTTPUtil getHttpUtil() {
		return this.httpUtil;
	}

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

	/**
	 * Permit operations only for the user himself, not in other's.
	 * @param id
	 */
	public void checkPermition(Integer id) {
		final String token = httpUtil.getRequestToken();
		final Integer tokenId = jwtUtil.getTokenId(token);
		if (!id.equals(tokenId) && 
			!httpUtil.hasRequestRole(RolesEnum.ADMIN)) {
			throw new AuthorizationException("Not authorized to execute operations in other clients");
		}
	}
}
