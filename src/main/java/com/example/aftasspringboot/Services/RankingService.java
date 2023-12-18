package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.Ranking;

import java.util.List;

public interface RankingService {

    List<Ranking> getRanking(String competitionCode);
    List<Member> getTopThree(String competitionCode);

}
