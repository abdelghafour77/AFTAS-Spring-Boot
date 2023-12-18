package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.RankingService;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.Ranking;
import com.example.aftasspringboot.repositories.CompetitionRepository;
import com.example.aftasspringboot.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {

    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;

    public RankingServiceImpl(MemberRepository memberRepository, CompetitionRepository competitionRepository) {
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public List<Ranking> getRanking(String competitionCode) {
        // Get the competition by its ID
        Competition competition = competitionRepository.findByCode(competitionCode);

        // Get all the rankings associated with this competition
        List<Ranking> rankings = competition.getRankings();

        // Sort the rankings based on the score in descending order
        rankings.sort((r1, r2) -> r2.getScore().compareTo(r1.getScore()));

        // Assign the rank based on the position in the list
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }



        return rankings;
    }

    @Override
    public List<Member> getTopThree(String competitionCode) {
        return null;
    }
}
