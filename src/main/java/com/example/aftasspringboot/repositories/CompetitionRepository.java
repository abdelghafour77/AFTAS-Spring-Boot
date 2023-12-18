package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Competition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT c FROM Competition c WHERE lower(c.name) LIKE lower(concat('%', :search, '%')) OR lower(c.code) LIKE lower(concat('%', :search, '%')) OR lower(c.location) LIKE lower(concat('%', :search, '%'))")
    List<Competition> findByNameOrCodeOrLocationLike(@Param("search") String search);

    @Query("SELECT c FROM Competition c WHERE lower(c.status) LIKE lower(concat('%', :status, '%'))")
    List<Competition> findByStatus(@Param("status") String status);

    //    get the big ranking of a competition
    @Query("SELECT r FROM Ranking r WHERE r.competition.id = :competitionId")
    List<Competition> findLastRanking(@Param("competitionId") Long competitionId);


}
