package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.enums.IdentityDocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Page<Member> findAll(Pageable pageable);
    List<Member> findAll(Specification<Member> specification);
    Optional<Member> findByIdentityNumber(String identityNumber);
    @Query("SELECT c FROM Member c WHERE lower(c.firstName) LIKE lower(concat('%', :search, '%')) OR lower(c.lastName) LIKE lower(concat('%', :search, '%')) OR lower(c.identityNumber) LIKE lower(concat('%', :search, '%'))")
    List<Member> findByCriteria(@Param("search") String search);
}
