package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.enums.IdentityDocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Page<Member> findAll(Pageable pageable);
    List<Member> findAll(Specification<Member> specification);
    Optional<Member> findByIdentityNumber(String identityNumber);

}
