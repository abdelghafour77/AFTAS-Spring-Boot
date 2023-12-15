package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.dtos.requests.LevelRequest;
import com.example.aftasspringboot.dtos.responses.LevelResponse;

import java.util.List;

public interface LevelService {

    public LevelResponse getLevelById(Long id);

    public LevelResponse getLevelByCode(Integer code);

    public List<LevelResponse> getAllLevels();

    public LevelResponse createLevel(LevelRequest levelRequest);

    public LevelResponse updateLevel(LevelRequest levelRequest, Long id);

    public void deleteLevel(Long id);
}
