package com.example.aftasspringboot.dtos.responses;

import com.example.aftasspringboot.entities.Fish;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FishResponse {
    private Long id;
    private String name;
    private Double avgWeight;
    private Long levelId;

    public static FishResponse fromEntity(Fish fish) {
        return FishResponse.builder()
                .id(fish.getId())
                .name(fish.getName())
                .avgWeight(fish.getAvgWeight())
                .levelId(fish.getLevel().getId())
                .build();
    }
    public static List<FishResponse> fromEntities(List<Fish> fishes) {
        return fishes.stream()
                .map(FishResponse::fromEntity)
                .toList();
    }

}
