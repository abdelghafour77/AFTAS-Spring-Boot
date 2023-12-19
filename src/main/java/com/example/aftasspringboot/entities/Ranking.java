package com.example.aftasspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {

    @EmbeddedId
    private RankId id;

    @Positive(message = "Rank must be positive")
    private Integer rank;

    @PositiveOrZero(message = "Score must be positive")
    @NotNull(message = "Score cannot be null")
    private Integer score;

    @ManyToOne
    @MapsId("memberId")
    @JoinColumn(name = "member_id")
    @NotNull(message = "Member cannot be null")
    @JsonIgnoreProperties({"rankings"})
    private Member member;

    @ManyToOne
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    @NotNull(message = "Competition cannot be null")
    @JsonIgnoreProperties({"rankings" , "huntings"})
    private Competition competition;
}
