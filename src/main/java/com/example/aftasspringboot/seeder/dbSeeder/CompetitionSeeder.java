package com.example.aftasspringboot.seeder.dbSeeder;

import com.example.aftasspringboot.entities.Competition;

import com.example.aftasspringboot.repositories.CompetitionRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CompetitionSeeder {
    private final CompetitionRepository competitionRepository;

    private final Competition[] competitions={
            Competition.builder().name("Safi")
                    .code("saf-23-12-29")
                    .location("Safi")
                    .price(120.0)
                    .description("La premiere edition de Safi")
                    .numberOfParticipants(50)
                    .status("finished")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2023,12,19))
                    .build(),
            Competition.builder().name("Rabat")
                    .code("rab-23-12-20")
                    .location("Rabat")
                    .price(120.0)
                    .description("La premiere edition de Rabat")
                    .numberOfParticipants(40)
                    .status("ongoing")
                    .startTime(java.time.LocalTime.of(9,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2023,12,20))
                    .build(),

            Competition.builder().name("Rabat")
                    .code("rab-23-12-21")
                    .location("Rabat")
                    .price(120.0)
                    .description("La deuxieme edition de Rabat")
                    .numberOfParticipants(40)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(12,0))
                    .date(java.time.LocalDate.of(2023,12,21))
                    .build(),
            Competition.builder().name("Tetouan")
                    .code("tet-23-12-22")
                    .location("Tetouan")
                    .price(120.0)
                    .description("Aftas Tetouan")
                    .numberOfParticipants(60)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2023,12,22))
                    .build(),
            Competition.builder().name("Tanger")
                    .code("tan-23-12-23")
                    .location("Tanger")
                    .price(120.0)
                    .description("La 5 eme edition de Aftas Tanger")
                    .numberOfParticipants(55)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2023,12,23))
                    .build(),
            Competition.builder().name("Dakhla sahara")
                    .code("dak-23-12-24")
                    .location("Dakhla")
                    .price(120.0)
                    .description("La 1 ere edition de Aftas Dakhl")
                    .numberOfParticipants(30)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2023,12,24))
                    .build(),
            Competition.builder().name("Agadir")
                    .code("aga-23-12-31")
                    .location("Agadir")
                    .price(120.0)
                    .description("La 3 eme edition de Aftas Agadir")
                    .numberOfParticipants(25)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2023,12,31))
                    .build(),
            Competition.builder().name("Casablanca")
                    .code("cas-24-01-01")
                    .location("Casablanca")
                    .price(120.0)
                    .description("La 4 eme edition de Aftas Casablanca")
                    .numberOfParticipants(60)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2024,1,1))
                    .build(),
            Competition.builder().name("Essaouira")
                    .code("ess-24-01-01")
                    .location("Essaouira")
                    .price(120.0)
                    .description("La 1 ere edition de Aftas Essaouira")
                    .numberOfParticipants(25)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2024,1,2))
                    .build(),
            Competition.builder().name("Oualidia")
                    .code("oua-24-01-03")
                    .location("Oualidia")
                    .price(120.0)
                    .description("La 1 ere edition de Aftas Oualidia")
                    .numberOfParticipants(20)
                    .status("upcoming")
                    .startTime(java.time.LocalTime.of(10,0))
                    .endTime(java.time.LocalTime.of(14,0))
                    .date(java.time.LocalDate.of(2024,1,3))
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
