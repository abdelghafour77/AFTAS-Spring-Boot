package com.example.aftasspringboot.Services;

import com.example.aftasspringboot.dtos.requests.FishRequest;
import com.example.aftasspringboot.dtos.responses.FishResponse;

import java.util.List;

public interface FishService {

    public List<FishResponse> getAllFishes();

    public FishResponse getFishByName(String name);

    public FishResponse getFishById(Long id);

    public FishResponse createFish(FishRequest fishRequest);

    public FishResponse updateFish(Long id, FishRequest fishRequest);

    public void deleteFish(Long id);

}
