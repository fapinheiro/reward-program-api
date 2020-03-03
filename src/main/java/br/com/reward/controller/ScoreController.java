/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reward.entity.Score;
import br.com.reward.service.ScoreService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class ScoreController {

    private final Logger LOG = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreService service;

    @GetMapping(path = "/scores")
    public Iterable<Score> getAllScores() throws Throwable {
        return service.findAll();
    }

    @GetMapping(path = "/scores/{id}")
    public Score getScoreById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching Score of id %d", id));
        return service.findById(id);
    }

    @PostMapping(path = "/scores")
    public Score addScore(@Valid @RequestBody Score score) throws Throwable {
        LOG.info(String.format("Posting score of id %s", score.getCodScore()));
        return service.save(score);
    }

    @PutMapping("/scores/{id}")
    public Score updateScore(@Valid @RequestBody Score newScore, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating Score of id %d", id));
        return service.update(id, newScore);
    }

    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Score> deleteScore(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting Score of id %d", id));
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
