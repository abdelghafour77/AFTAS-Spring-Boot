package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    Competition findByCode(String code);
    Page<Competition> findByCode(String code, Pageable pageable);
    Optional<Competition> findByDate(LocalDate date);
    List<Competition> findAll(Specification<Competition> specification);

}
