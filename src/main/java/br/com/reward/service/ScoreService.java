/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.service;

import br.com.reward.entity.Score;

public interface ScoreService {

	public Iterable<Score> findAll() throws Throwable;

	public Score save(final Score score) throws Throwable;

	public Score findById(final Integer id) throws Throwable;

	public Score update(final Integer id, final Score newScore) throws Throwable;

	public void delete(final Integer id) throws Throwable;
}
