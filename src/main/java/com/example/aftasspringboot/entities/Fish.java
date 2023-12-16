package com.example.aftasspringboot.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Average weight cannot be null")
    @Positive(message = "Average weight must be positive")
    @Max(value = 5000, message = "Average weight must be less than or equal to 5000")
    private Double avgWeight;

    @NotNull(message = "Level cannot be null")
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;


}
