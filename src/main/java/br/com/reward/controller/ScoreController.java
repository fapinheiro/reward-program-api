/**
 * @author filipe.pinheiro, 03/03/2020
 */
package br.com.reward.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.reward.entity.Score;
import br.com.reward.enums.GoodTypeEnum;
import br.com.reward.service.ScoreService;

import java.net.URI;
import java.time.OffsetDateTime;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class ScoreController {

    private final Logger LOG = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreService service;
            
    @GetMapping(path = "/scores")
    public Iterable<Score> getAllScores(
        @RequestParam(required=false) GoodTypeEnum scoreType,
        @RequestParam(required=false) Integer creditMin,
        @RequestParam(required=false) Integer creditMax, 
        @RequestParam(required=false) Integer instMin,
        @RequestParam(required=false) Integer instMax,
        @RequestParam(required=false) Integer score,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime startCreationAt,
        @RequestParam(required=false) @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime endCreationAt
    ) throws Throwable {
        return service.findAllByParameters(scoreType, creditMin, creditMax, instMin,
                                instMax, score, startCreationAt, endCreationAt);
    }

    @GetMapping(path = "/scores/{id}")
    public ResponseEntity<Score> getScoreById(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Searching Score of id %d", id));
        Score client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping(path = "/scores")
    public ResponseEntity<Score> addScore(@Valid @RequestBody Score score) throws Throwable {
        LOG.info(String.format("Posting score of id %s", score.getCodScore()));
        Score newScore = service.save(score);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newScore.getCodScore())
            .toUri();
        return ResponseEntity.created(uri).body(newScore);
    }

    @PutMapping("/scores/{id}")
    public ResponseEntity<Score> updateScore(@Valid @RequestBody Score newScore, @PathVariable Integer id)
            throws Throwable {
        LOG.info(String.format("Updating Score of id %d", id));
        Score score = service.update(id, newScore);
        return ResponseEntity.ok().body(score);
    }

    @DeleteMapping("/scores/{id}")
    public ResponseEntity<Score> deleteScore(@PathVariable Integer id) throws Throwable {
        LOG.info(String.format("Deleting Score of id %d", id));
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
