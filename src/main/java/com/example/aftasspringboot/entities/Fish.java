package com.example.aftasspringboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fish {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double AvgWeight;

}
