package com.example.aftasspringboot.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingRequest {

    @NotNull(message = "Member id cannot be null")
    private Long memberId;

    @NotNull(message = "Competition id cannot be null")
    private Long competitionId;
}
