package com.example.aftasspringboot.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequest {


    @Column(unique = true)
    @Positive(message = "Code must be positive")
    @NotNull(message = "Code cannot be null")
    private Integer code;


    @NotNull(message = "Description cannot be null")
    private String description;

    @Column(unique = true)
    @Positive(message = "Points must be positive")
    @NotNull(message = "Points cannot be null")
    private Integer points;


}
