package com.example.aftasspringboot.dtos.responses;

import com.example.aftasspringboot.dtos.requests.LevelRequest;
import com.example.aftasspringboot.entities.Fish;
import com.example.aftasspringboot.entities.Level;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter

@Builder
public class LevelResponse {

    private Integer code;
    private String description;
    private Integer points;

    public static LevelResponse fromEntity(Level level) {
        return LevelResponse.builder()
                .code(level.getCode())
                .description(level.getDescription())
                .points(level.getPoints())
                .build();
    }

    public static List<LevelResponse> fromEntities(List<Level> levels) {
        return levels.stream()
                .map(LevelResponse::fromEntity)
                .toList();
    }

    public static Level toEntity(LevelResponse levelResponse) {
        return Level.builder()
                .code(levelResponse.getCode())
                .description(levelResponse.getDescription())
                .points(levelResponse.getPoints())
                .build();
    }

    public static Level fromRequest(LevelRequest levelRequest) {
        return Level.builder()
                .code(levelRequest.getCode())
                .description(levelRequest.getDescription())
                .points(levelRequest.getPoints())
                .build();
    }

}
