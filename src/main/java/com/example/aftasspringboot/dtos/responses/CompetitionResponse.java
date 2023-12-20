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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public static Page<CompetitionResponse> fromEntities(Page<Competition> competitions) {

        List<CompetitionResponse> competitionResponses = competitions.stream()
                .map(CompetitionResponse::fromEntity)
                .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(0, competitionResponses.size());
        return new PageImpl<>(competitionResponses, pageable, competitionResponses.size());
    }
}
