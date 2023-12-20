package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.dtos.requests.CompetitionRequest;
import com.example.aftasspringboot.dtos.responses.CompetitionResponse;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {

    public List<Competition> getAllCompetitions();

    public Page<CompetitionResponse> getAllPageableCompetitions(Pageable pageable);

    public Competition getCompetitionById(Long id);

    public Competition getCompetitionByCode(String code);

    public Competition createCompetition(CompetitionRequest competitionRequest);

    public Competition updateCompetition(CompetitionRequest competition, Long competitionId);

    public void deleteCompetition(Long id);

    public void results(Long id);

    public Page<CompetitionResponse> searchCompetitions(String value, Pageable pageable);

    public Page<CompetitionResponse> getCompetitionsByStatus(String status, Pageable pageable);

    public Competition register(String code, Long memberId);

    public List<Member> getParticipants(String code);

    public List<Member> getParticipantsWithSearch(String code, String search);
}
