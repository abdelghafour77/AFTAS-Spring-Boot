package com.example.aftasspringboot.web.controllers;

import com.example.aftasspringboot.Services.CompetitionService;
import com.example.aftasspringboot.dtos.requests.CompetitionRequest;
import com.example.aftasspringboot.entities.Competition;
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

    @GetMapping
    public List<Competition> getCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/{competitionId}")
    public Competition getCompetition(@PathVariable Long competitionId) {
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

//    @GetMapping("/topthree/{competitionId}")
//    public ResponseEntity<?> getTopThree(@PathVariable Long competitionId) {
//
//        Map<Integer, List<Top>> topThree = competitionService.getTopThree(competitionId);
//        return new ResponseEntity<>(topThree, HttpStatus.OK);
//    }

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

//    @PostMapping("/searchByCriteria")
//    public ResponseEntity<?> searchCompetitionsByCriteria(@RequestBody List<FilterDTO> filters) {
//        List<Competition> competitions = competitionService.searchCompetitionsByCriteria(filters);
//        return new ResponseEntity<>(competitions, HttpStatus.OK);
//    }

    @GetMapping("/search/{value}")
    public ResponseEntity<?> searchCompetitions(@PathVariable String value) {
        List<Competition> competitions = competitionService.searchCompetitions(value);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @DeleteMapping("/{competitionId}")
    public ResponseEntity<?> deleteCompetition(@PathVariable Long competitionId) {
        competitionService.deleteCompetition(competitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}