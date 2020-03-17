/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.reward.entity.Score;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Integer> {
    
    @Query(
		value = "SELECT * FROM SCORES S " +
                "WHERE S.GOOD_TYPE in ?1 AND " +
                "S.CREDIT_MIN >= ?2 AND " +
                "S.CREDIT_MAX <= ?3 AND " +
                "S.INST_MIN >= ?4 AND " +
                "S.INST_MAX <= ?5 AND " +
                "S.SCORE >= ?6 AND " +
				"S.CREATION_AT BETWEEN ?7 AND ?8",
                // value = "SELECT * FROM SCORES S " +
                // "WHERE S.CREDIT_MIN >= ?1 AND " +
                // "S.CREDIT_MAX <= ?2 AND " +
                // "S.INST_MIN >= ?3 AND " +
                // "S.INST_MAX <= ?4 ",
		nativeQuery = true
	)
	Iterable<Score> findAllByParameters(
            List<String> scoreTypes,
			Integer creditMin, 
            Integer creditMax, 
            Integer instMin,
            Integer instMax,
            Integer score,
            String startCreationAt,
            String endCreationAt
    );
}
