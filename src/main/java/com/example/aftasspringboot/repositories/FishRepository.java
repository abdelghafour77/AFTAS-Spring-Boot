package com.example.aftasspringboot.repositories;

import com.example.aftasspringboot.entities.Fish;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, Long> {

    List<Fish> findAll(Specification<Fish> specification);

    Optional<Fish> findByName(String name);

}
