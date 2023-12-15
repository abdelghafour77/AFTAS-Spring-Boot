package com.example.aftasspringboot.seeder.dbSeeder;

import com.example.aftasspringboot.entities.Fish;
import com.example.aftasspringboot.entities.Level;
import com.example.aftasspringboot.repositories.FishRepository;
import com.example.aftasspringboot.repositories.LevelRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class FishSeeder {
    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;
    private final Fish[] fishes = {
            Fish.builder().name("Tuna").avgWeight(5.0).level(Level.builder().code(9).build()).build(),
            Fish.builder().name("Salmon").avgWeight(2.0).level(Level.builder().code(2).build()).build(),
            Fish.builder().name("Flounder").avgWeight(0.7).level(Level.builder().code(1).build()).build(),
            Fish.builder().name("Perch").avgWeight(0.8).level(Level.builder().code(1).build()).build(),
            Fish.builder().name("Carp").avgWeight(3.0).level(Level.builder().code(3).build()).build(),
            Fish.builder().name("Cod").avgWeight(2.0).level(Level.builder().code(2).build()).build(),
            Fish.builder().name("Haddock").avgWeight(1.5).level(Level.builder().code(2).build()).build(),
            Fish.builder().name("Red Snapper").avgWeight(2.0).level(Level.builder().code(2).build()).build(),
            Fish.builder().name("Snapper").avgWeight(2.5).level(Level.builder().code(2).build()).build(),
            Fish.builder().name("Bass").avgWeight(3.0).level(Level.builder().code(3).build()).build(),
            Fish.builder().name("Trout").avgWeight(1.0).level(Level.builder().code(1).build()).build(),
            Fish.builder().name("Grouper").avgWeight(4.0).level(Level.builder().code(5).build()).build(),
            Fish.builder().name("Mahi-Mahi").avgWeight(7.0).level(Level.builder().code(6).build()).build()
    };

    public FishSeeder(FishRepository fishRepository, LevelRepository levelRepository) {
        this.fishRepository = fishRepository;
        this.levelRepository = levelRepository;
    }
    private void log(){
        System.out.println("----------------------Fish Seeder----------------------");
    }
    public void seed() {
        this.log();
        if(fishRepository.findAll().isEmpty())
            Arrays.stream(fishes).forEach(fish -> {
                Optional<Level> optionalLevel = levelRepository.getLevelByCode(fish.getLevel().getCode());
                if(optionalLevel.isPresent()){
                    fish.setLevel(optionalLevel.get());
                    fishRepository.save(fish);
                }
            });
    }
}
