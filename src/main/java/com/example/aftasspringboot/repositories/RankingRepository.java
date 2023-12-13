package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.Ranking;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByMemberAndCompetition(Member member, Competition competition);

    Boolean existsByMemberAndCompetition(Member member, Competition competition);

    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);

    List<Ranking> findAll(Specification<Ranking> specification);
}
