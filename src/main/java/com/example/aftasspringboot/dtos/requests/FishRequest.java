package com.example.aftasspringboot.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FishRequest {

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must only contain letters")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    @Positive(message = "Average weight must be positive")
    @Max(value = 5000, message = "Average weight must be less than or equal to 5000")
    private Double avgWeight;

    @NotNull(message = "Level id cannot be null")
    private Long levelId;
}
