package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.CompetitionService;
import com.example.aftasspringboot.dtos.requests.CompetitionRequest;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.repositories.CompetitionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Page<Competition> getAllCompetitionsWithPagination(Pageable pageable) {
        return null;
    }

    @Override
    public Competition getCompetitionById(Long id) {
        if(competitionRepository.findById(id).isPresent())
            return competitionRepository.findById(id).get();
        else
            throw new RuntimeException("Competition not found");
    }

    @Override
    public Competition getCompetitionByCode(String code) {
        if(competitionRepository.findByCode(code) != null)
            return competitionRepository.findByCode(code);
        else
            throw new RuntimeException("Competition not found");
    }


    @Override
    public Competition createCompetition(CompetitionRequest competitionRequest) {

        if(competitionRequest.getDate().isBefore(java.time.LocalDate.now()))
            throw new RuntimeException("Date must be in the future");
        if (competitionRequest.getStartTime().isAfter(competitionRequest.getEndTime()))
            throw new RuntimeException("Start time must be before end time");
        if(competitionRequest.getDate().isBefore(java.time.LocalDate.now().plusDays(2)))
            throw new RuntimeException("Date must be within 2 days");
        else{
            String code = generateCode(competitionRequest.getLocation(), competitionRequest.getDate());
            if (competitionRepository.findByCode(code) != null)
                throw new RuntimeException("Competition with this code already exists");
            else{
            Competition competition = Competition.builder()
                    .code(code)
                    .date(competitionRequest.getDate())
                    .name(competitionRequest.getName())
                    .description(competitionRequest.getDescription())
                    .startTime(competitionRequest.getStartTime())
                    .endTime(competitionRequest.getEndTime())
                    .numberOfParticipants(competitionRequest.getNumberOfParticipants())
                    .location(competitionRequest.getLocation())
                    .price(competitionRequest.getPrice())
                    .build();

            return competitionRepository.save(competition);}
        }
    }

    @Override
    public Competition updateCompetition(CompetitionRequest competition, Long competitionId) {

        if(competition.getDate().isBefore(java.time.LocalDate.now()))
            throw new RuntimeException("Date must be in the future");
        if (competition.getStartTime().isAfter(competition.getEndTime()))
            throw new RuntimeException("Start time must be before end time");
        if(competition.getDate().isBefore(java.time.LocalDate.now().plusDays(2)))
            throw new RuntimeException("Date must be within 2 days");
        else{
            Competition competition1 = competitionRepository.findById(competitionId).orElseThrow(() -> new RuntimeException("Competition not found"));
            competition1.setDate(competition.getDate());
            competition1.setName(competition.getName());
            competition1.setDescription(competition.getDescription());
            competition1.setStartTime(competition.getStartTime());
            competition1.setEndTime(competition.getEndTime());
            competition1.setNumberOfParticipants(competition.getNumberOfParticipants());
            competition1.setLocation(competition.getLocation());
            competition1.setPrice(competition.getPrice());
            return competitionRepository.save(competition1);
        }
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found"));
        competitionRepository.deleteById(id);
    }

    @Override
    public void results(Long id) {

    }
//
//    @Override
//    public List<Competition> searchCompetitionsByCriteria(List<FilterDTO> filters) {
//        return null;
//    }

    @Override
    public List<Competition> searchCompetitions(String value) {
        return null;
    }


    public static String generateCode(String location, LocalDate date) {
        String locationCode = location.substring(0, Math.min(location.length(), 3)).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }
}
