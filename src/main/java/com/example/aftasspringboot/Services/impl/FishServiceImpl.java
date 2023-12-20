package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.FishService;
import com.example.aftasspringboot.dtos.requests.FishRequest;
import com.example.aftasspringboot.dtos.responses.FishResponse;
import com.example.aftasspringboot.entities.Fish;
import com.example.aftasspringboot.handler.exception.costumExceptions.ValidationException;
import com.example.aftasspringboot.repositories.FishRepository;
import com.example.aftasspringboot.repositories.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;


    public FishServiceImpl(FishRepository fishRepository, LevelRepository levelRepository) {
        this.fishRepository = fishRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public List<FishResponse> getAllFishes() {
        List<Fish> fishes = fishRepository.findAll();
        return FishResponse.fromEntities(fishes);
    }

    @Override
    public FishResponse getFishByName(String name) {
        Fish fish = fishRepository.findByName(name).orElseThrow(() -> new ValidationException("Fish not found"));
        return FishResponse.fromEntity(fish);
    }

    @Override
    public FishResponse getFishById(Long id) {
        Fish fish = fishRepository.findById(id).orElseThrow(() -> new ValidationException("Fish not found"));
        return FishResponse.fromEntity(fish);
    }

    @Override
    public FishResponse createFish(FishRequest fishRequest) {
        if (fishRepository.findByName(fishRequest.getName()).isPresent()) {
            throw new ValidationException("Fish already exists");
        }
        Fish fish = Fish.builder()
                .name(fishRequest.getName())
                .level(levelRepository.findById(fishRequest.getLevelId()).orElseThrow(() -> new ValidationException("Level not found")))
                .avgWeight(fishRequest.getAvgWeight())
                .build();

        return FishResponse.fromEntity(fishRepository.save(fish));
    }

    @Override
    public FishResponse updateFish(Long id, FishRequest fishRequest) {
        Fish fish = fishRepository.findById(id).orElseThrow(() -> new ValidationException("Fish not found"));
        fish.setName(fishRequest.getName());
        fish.setAvgWeight(fishRequest.getAvgWeight());
        fish.setLevel(levelRepository.findById(fishRequest.getLevelId()).orElseThrow(() -> new ValidationException("Level not found")));
        return FishResponse.fromEntity(fishRepository.save(fish));
    }

    @Override
    public void deleteFish(Long id) {
        Fish fish = fishRepository.findById(id).orElseThrow(() -> new ValidationException("Fish not found"));
        fishRepository.delete(fish);
    }
}
