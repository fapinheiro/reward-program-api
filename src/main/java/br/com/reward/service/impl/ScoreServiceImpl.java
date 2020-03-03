/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import br.com.reward.entity.Score;
import br.com.reward.exception.NotFoundException;
import br.com.reward.repository.ScoreRepository;
import br.com.reward.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Value(value = "${reward.records.max-records:10}")
	private Integer maxRecords;

	@Autowired
	private ScoreRepository dao;

	@Override
	public Iterable<Score> findAll() {
		return dao.findAll();
	}

	public Score save(final Score score) throws Throwable {

		// Define default date
		if (score.getCreationAt() == null) {
			score.setCreationAt(new Date());
		}

		return dao.save(score);
	}

	public Score findById(final Integer id) throws Throwable {
		return dao.findById(id).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Score of id {%d} not found for selecting", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public Score update(final Integer id, final Score newScore) throws Throwable {
		return dao.findById(id).map(score -> {
			score.setCreditMax(newScore.getCreditMax());
			score.setCreditMin(newScore.getCreditMin());
			score.setGoodType(newScore.getGoodType());
			score.setInstMax(newScore.getInstMax());
			score.setInstMin(newScore.getInstMin());
			score.setScore(newScore.getScore());
			score.setUpdatedAt(new Date());
			return dao.save(score);
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Score of id {%d} not found for updating", id));
		});
	}

	@Transactional(isolation = Isolation.SERIALIZABLE, timeout = 5)
	public void delete(final Integer id) throws Throwable {
		dao.findById(id).map(ind -> {
			dao.deleteById(id);
			return ind;
		}).orElseThrow(() -> {
			throw new NotFoundException(String.format("A Score of id {%d} not found for deleting", id));
		});
	}

}
