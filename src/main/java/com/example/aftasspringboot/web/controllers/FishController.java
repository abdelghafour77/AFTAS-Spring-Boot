package com.example.aftasspringboot.web.controllers;

import com.example.aftasspringboot.Services.FishService;
import com.example.aftasspringboot.dtos.requests.FishRequest;
import com.example.aftasspringboot.dtos.responses.FishResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fishes")
@AllArgsConstructor
public class FishController {

    private final FishService fishService;

    @GetMapping()
    public List<FishResponse> getAllFishes() {
        return fishService.getAllFishes();
    }

    @GetMapping("/{name}")
    public FishResponse getFishByName(@PathVariable String name) {
        return fishService.getFishByName(name);
    }

    @PostMapping()
    public FishResponse createFish(@RequestBody FishRequest fishRequest) {
        return fishService.createFish(fishRequest);
    }

    @PutMapping("/{id}")
    public FishResponse updateFish(@PathVariable Long id, @RequestBody FishRequest fishRequest) {
        return fishService.updateFish(id, fishRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteFish(@PathVariable Long id) {
        fishService.deleteFish(id);
    }

}
