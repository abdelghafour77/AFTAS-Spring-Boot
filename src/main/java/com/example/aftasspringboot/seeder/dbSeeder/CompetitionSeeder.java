package com.example.aftasspringboot.seeder.dbSeeder;

import com.example.aftasspringboot.entities.Competition;

import com.example.aftasspringboot.repositories.CompetitionRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CompetitionSeeder {
    private final CompetitionRepository competitionRepository;

    private final Competition[] competitions={
            Competition.builder().name("Rabat edition 2")
                    .code("rab-23-12-30")
                    .location("Rabat")
                    .price(120.0)
                    .description("La deuxieme edition de Rabat")
                    .numberOfParticipants(60)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2023,12,30))
                    .build(),
            Competition.builder().name("Tetouan Aftas 2")
                    .code("tet-23-12-31")
                    .location("Tetouan")
                    .price(120.0)
                    .description("Aftas Tetouan")
                    .numberOfParticipants(60)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2023,12,31))
                    .build(),
            Competition.builder().name("Tanger 5")
                    .code("tan-24-07-15")
                    .location("Tanger")
                    .price(120.0)
                    .description("La 5 eme edition de Aftas Tanger")
                    .numberOfParticipants(60)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2024,7,15))
                    .build(),
            Competition.builder().name("Dakhl sahara")
                    .code("dak-24-07-16")
                    .location("Dakhl")
                    .price(120.0)
                    .description("La 1 ere edition de Aftas Dakhl")
                    .numberOfParticipants(60)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2024,7,16))
                    .build(),

    };
    public CompetitionSeeder(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }
    private void log(){
        System.out.println("----------------------Competition Seeder----------------------");
    }
    public void seed() {
        this.log();
        if(competitionRepository.findAll().isEmpty())
            Arrays.stream(competitions).forEach(competition -> {
                competitionRepository.save(competition);
            });
    }
}
