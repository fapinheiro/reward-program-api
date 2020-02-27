/**
 * @author filipe.pinheiro, 27/02/2020
 */
package br.com.reward.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.Parameter;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, Integer> {
	
}
