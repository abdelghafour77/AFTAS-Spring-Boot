package com.example.aftasspringboot.seeder;

import com.example.aftasspringboot.seeder.dbSeeder.CompetitionSeeder;
import com.example.aftasspringboot.seeder.dbSeeder.FishSeeder;
import com.example.aftasspringboot.seeder.dbSeeder.LevelSeeder;
import com.example.aftasspringboot.seeder.dbSeeder.MemberSeeder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class AppSeeder {
    private final FishSeeder fishSeeder;
    private final LevelSeeder levelSeeder;
    private final CompetitionSeeder competitionSeeder;
    private final MemberSeeder memberSeeder;

    public AppSeeder(FishSeeder fishSeeder, LevelSeeder levelSeeder, CompetitionSeeder competitionSeeder, MemberSeeder memberSeeder) {
        this.fishSeeder = fishSeeder;
        this.levelSeeder = levelSeeder;
        this.competitionSeeder = competitionSeeder;
        this.memberSeeder = memberSeeder;
    }
    @PostConstruct
    public void init() {
        levelSeeder.seed();
        fishSeeder.seed();
        competitionSeeder.seed();
        memberSeeder.seed();
    }
}