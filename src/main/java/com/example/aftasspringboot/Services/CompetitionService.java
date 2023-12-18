package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.dtos.requests.CompetitionRequest;
import com.example.aftasspringboot.dtos.responses.CompetitionResponse;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {
    public List<CompetitionResponse> getAllCompetitions();

    public Page<Competition> getAllCompetitionsWithPagination(Pageable pageable);

    public Competition getCompetitionById(Long id);

    public Competition getCompetitionByCode(String code);

//    public List<Member> getParticipants(Competition competition);

    public Competition createCompetition(CompetitionRequest competitionRequest);

    public Competition updateCompetition(CompetitionRequest competition , Long competitionId);

    public void deleteCompetition(Long id);

    public void results(Long id);

//    public Map<Integer, List<Top>> getTopThree(Long competitionId);

//    public List<Competition> searchCompetitionsByCriteria(List<FilterDTO> filters);

    public List<CompetitionResponse> searchCompetitions(String value);

    public List<CompetitionResponse> getCompetitionsByStatus(String status);

    public Competition register(String code, Long memberId);

    public List<Member> getParticipants(String code);

    public List<Member> getParticipantsWithSearch(String code , String search);
}
