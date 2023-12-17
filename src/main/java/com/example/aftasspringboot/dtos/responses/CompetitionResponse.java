package com.example.aftasspringboot.dtos.responses;

import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Hunting;
import com.example.aftasspringboot.entities.Ranking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
@Builder
@Getter
@Setter
public class CompetitionResponse {

    private String code;

    private String name;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private int numberOfParticipants;

    private int numberOfParticipantsRegistered;

    private String location;

    private String status;

    private Double price;

    private String description;


    public static CompetitionResponse fromEntity(Competition competition) {
        return CompetitionResponse.builder()
                .code(competition.getCode())
                .name(competition.getName())
                .date(competition.getDate())
                .startTime(competition.getStartTime())
                .endTime(competition.getEndTime())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .numberOfParticipantsRegistered(competition.getRankings().size())
                .location(competition.getLocation())
                .status(competition.getStatus())
                .price(competition.getPrice())
                .description(competition.getDescription())
                .build();
    }
    public static List<CompetitionResponse> fromEntities(List<Competition> competitions) {
        return competitions.stream()
                .map(CompetitionResponse::fromEntity)
                .toList();
    }
}
