/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.Score;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {
	
}
