/**
 * @author filipe.pinheiro, 27/02/2020
*/
package br.com.reward.service;

import br.com.reward.entity.Parameter;

public interface ParameterService {
	
	public Iterable<Parameter> findAll();

	public Parameter update(final Integer id, final Parameter newParameter) throws Throwable;

}
