package com.example.aftasspringboot.web.controllers;

import com.example.aftasspringboot.Services.CompetitionService;
import com.example.aftasspringboot.Services.RankingService;
import com.example.aftasspringboot.dtos.requests.CompetitionRequest;
import com.example.aftasspringboot.dtos.responses.CompetitionResponse;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Ranking;

import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
@AllArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;
    private final RankingService rankingService;

    @GetMapping
    public Page<CompetitionResponse> getCompetitions(@ParameterObject Pageable pageable) {
        return competitionService.getAllCompetitions(pageable);
    }

    @GetMapping("/{competitionId}")
    public Competition getCompetition( @PathVariable Long competitionId) {
        return competitionService.getCompetitionById(competitionId);
    }

    @GetMapping("/code/{competitionCode}")
    public Competition getCompetitionByCode(@PathVariable String competitionCode) {
        return competitionService.getCompetitionByCode(competitionCode);
    }

    @GetMapping("/results/{competitionId}")
    public ResponseEntity<?> getResults(@PathVariable Long competitionId) {
        competitionService.results(competitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public Page<CompetitionResponse> getCompetitionsByStatus(@PathVariable String status,@ParameterObject Pageable pageable) {
        return competitionService.getCompetitionsByStatus(status,pageable);
    }

    @GetMapping("/topThree/{competitionCode}")
    public ResponseEntity<?> getTopThree(@PathVariable String competitionCode) {
        List<Ranking> topThree = rankingService.getRanking(competitionCode);
        return new ResponseEntity<>(topThree, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createCompetition(@Valid @RequestBody CompetitionRequest competitionRequest) {
        Competition addedCompetition = competitionService.createCompetition(competitionRequest);
        return new ResponseEntity<>(addedCompetition, HttpStatus.CREATED);
    }

    @PutMapping("/{competitionId}")
    public ResponseEntity<?> updateCompetition(@PathVariable Long competitionId,@Valid @RequestBody CompetitionRequest competitionRequest) {
        Competition updatedCompetition = competitionService.updateCompetition(competitionRequest, competitionId);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }

    @GetMapping("/search/{value}")
    public Page<CompetitionResponse> searchCompetitions(@PathVariable String value ,@ParameterObject Pageable pageable) {
        return competitionService.searchCompetitions(value, pageable);

    }

    @GetMapping("/registered-members/{competitionCode}")
    public ResponseEntity<?> getParticipants(@PathVariable String competitionCode) {
        return new ResponseEntity<>(competitionService.getParticipants(competitionCode), HttpStatus.OK);
    }
    @GetMapping("/registered-members/{competitionCode}/{search}")
    public ResponseEntity<?> getParticipantsWithSearch(@PathVariable String competitionCode , @PathVariable String search) {
        return new ResponseEntity<>(competitionService.getParticipantsWithSearch(competitionCode , search), HttpStatus.OK);
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity<?> deleteCompetition(@PathVariable Long competitionId) {
        competitionService.deleteCompetition(competitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/register/{competitionCode}/{memberId}")
    public ResponseEntity<?> register(@PathVariable String competitionCode, @PathVariable Long memberId) {
        Competition competition = competitionService.register(competitionCode, memberId);
        return new ResponseEntity<>(competition, HttpStatus.OK);
    }

}
