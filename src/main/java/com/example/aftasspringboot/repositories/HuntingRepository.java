package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Fish;
import com.example.aftasspringboot.entities.Hunting;
import com.example.aftasspringboot.entities.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    Optional<Hunting> findByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);
    List<Hunting> findAll(Specification<Hunting> specification);
}
