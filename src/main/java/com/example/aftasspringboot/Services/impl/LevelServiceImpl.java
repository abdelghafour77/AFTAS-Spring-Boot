package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.LevelService;
import com.example.aftasspringboot.dtos.requests.LevelRequest;
import com.example.aftasspringboot.dtos.responses.LevelResponse;
import com.example.aftasspringboot.entities.Level;
import com.example.aftasspringboot.repositories.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    public final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository ) {
        this.levelRepository = levelRepository;
    }

    @Override
    public LevelResponse getLevelById(Long id) {
        Level level= levelRepository.findById(id).orElseThrow(() -> new RuntimeException("Level not found"));
        return LevelResponse.fromEntity(level);
    }

    @Override
    public LevelResponse getLevelByCode(Integer code) {
        Level level= levelRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Level not found"));
        return LevelResponse.fromEntity(level);
    }

    @Override
    public List<LevelResponse> getAllLevels() {
        List<Level> levels = levelRepository.findAll();
        return LevelResponse.fromEntities(levels);
    }

    @Override
    public LevelResponse createLevel(LevelRequest levelRequest) {
        Level level = LevelResponse.fromRequest(levelRequest);
//       check if level code is bigger then the last level code and does not exist
        Level lastLevel = levelRepository.findTopByOrderByIdDesc().orElse(null);
        if (lastLevel != null) {
            if (level.getCode() <= lastLevel.getCode()) {
                throw new RuntimeException("Level code must be bigger than the last level code");
            }
        }
//        levelRepository.findByCode(level.getCode()).ifPresent((existingLevel) -> {
//            throw new RuntimeException("Level with code " + existingLevel.getCode() + " already exists");
//        });

//        check if level points is bigger then the last level points and does not exist
        if (lastLevel != null) {
            if (level.getPoints() <= lastLevel.getPoints()) {
                throw new RuntimeException("Level points must be bigger than the last level points");
            }
        }

        level = levelRepository.save(level);
        return LevelResponse.fromEntity(level);
    }

    @Override
    public LevelResponse updateLevel(LevelRequest levelRequest, Long id) {
        Level level = levelRepository.findById(id).orElseThrow(() -> new RuntimeException("Level not found"));
        level.setCode(levelRequest.getCode());

        level.setDescription(levelRequest.getDescription());
        level.setPoints(levelRequest.getPoints());
        level = levelRepository.save(level);
        return LevelResponse.fromEntity(level);
    }

    @Override
    public void deleteLevel(Long id) {
        Level level = levelRepository.findById(id).orElseThrow(() -> new RuntimeException("Level not found"));
        levelRepository.delete(level);
    }
}
