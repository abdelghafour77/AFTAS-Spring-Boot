package com.example.aftasspringboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({ AuditingEntityListener.class })
public class Competition {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    @NotNull(message = "Code cannot be null")
    @NotBlank(message = "Code cannot be blank")
    private String code;
    private String name;
    @Column(unique = true)
    @NotNull(message = "Name cannot be null")
    @Future(message = "Date must be in the future")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalTime endTime;

    @NotNull(message = "Number of participants cannot be null")
    @Positive(message = "Number of participants must be positive")
    private int numberOfParticipants;

    @NotNull(message = "Location cannot be null")
    @NotBlank(message = "Location cannot be blank")
    private String Location;
    @NotNull(message = "Type cannot be null")
    private String type;
    private String status;

    @NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount must be positive")
    private Double price;

    private String description;

    @OneToMany(mappedBy = "competition")
    @Column(nullable = true)
    @JsonIgnoreProperties("competition")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition")
    @Column(nullable = true)
    @JsonIgnoreProperties({"competition"})
    private List<Hunting> huntings;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}