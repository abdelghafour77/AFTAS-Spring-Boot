package com.example.aftasspringboot.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HuntingRequest {

    @NotNull(message = "Fish id cannot be null")
    private Long fishId;

    @NotNull(message = "Weight cannot be null")
    @Positive(message = "Weight must be positive")
    @Max(value = 5000, message = "Weight must be less than or equal to 5000")
    private Double weightOfHuntedFish;

    @NotNull(message = "Member id cannot be null")
    private Long memberId;

    @NotNull(message = "Competition id cannot be null")
    private Long competitionId;
}
