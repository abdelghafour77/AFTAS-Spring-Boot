package com.example.aftasspringboot.web.controllers;

import com.example.aftasspringboot.Services.LevelService;
import com.example.aftasspringboot.dtos.requests.LevelRequest;
import com.example.aftasspringboot.dtos.responses.LevelResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/levels")
public class LevelController {

    private final LevelService levelService;

    @GetMapping()
    public List<LevelResponse> getAllLevels() {
        return levelService.getAllLevels();
    }

    @PostMapping()
    public LevelResponse createLevel(@RequestBody LevelRequest levelRequest) {
        return levelService.createLevel(levelRequest);
    }

    @GetMapping("/{code}")
    public LevelResponse getLevelById(@PathVariable Integer code) {
        return levelService.getLevelByCode(code);
    }

    @PutMapping("/{id}")
    public LevelResponse updateLevel(@RequestBody LevelRequest levelRequest, @PathVariable Long id) {
        return levelService.updateLevel(levelRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable Long id) {
        levelService.deleteLevel(id);
    }



}
