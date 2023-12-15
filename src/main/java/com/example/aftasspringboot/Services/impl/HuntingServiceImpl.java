package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.HuntingService;
import com.example.aftasspringboot.dtos.requests.HuntingRequest;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Fish;
import com.example.aftasspringboot.entities.Hunting;
import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.repositories.CompetitionRepository;
import com.example.aftasspringboot.repositories.FishRepository;
import com.example.aftasspringboot.repositories.HuntingRepository;
import com.example.aftasspringboot.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final MemberRepository memberRepository;
private final CompetitionRepository competitionRepository;


    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository, MemberRepository memberRepository, CompetitionRepository competitionRepository) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Hunting addHunting(HuntingRequest huntingRequest) {
        // Retrieve the average weight of the fish
        Fish fish = fishRepository.findById(huntingRequest.getFishId())
                .orElseThrow(() -> new RuntimeException("Fish not found"));

        Double avgWeight = fish.getAvgWeight();

        // Compare the weight of the hunted fish with the average weight
        if (huntingRequest.getWeightOfHuntedFish() > avgWeight) {
            // Check if a hunting record already exists for this fish
            Member member = memberRepository.findById(huntingRequest.getMemberId())
                    .orElseThrow(() -> new RuntimeException("Member not found"));

            Competition competition = competitionRepository.findById(huntingRequest.getCompetitionId())
                    .orElseThrow(() -> new RuntimeException("Competition not found"));

            Optional<Hunting> existingHunting = huntingRepository.findByCompetitionAndMemberAndFish(competition, member, fish);

            if (existingHunting.isPresent()) {
                // If a hunting record exists, increment the numberOfFish
                Hunting existingHunt = existingHunting.get();
                existingHunt.setNumberOfFish(existingHunt.getNumberOfFish() + 1);
                return huntingRepository.save(existingHunt);
            } else {
                // If a hunting record does not exist, create a new Hunting entity and set numberOfFish to 1
                Hunting hunting = new Hunting();
                hunting.setFish(fish);
                hunting.setMember(member);
                hunting.setCompetition(competition);
                hunting.setNumberOfFish(1);
                return huntingRepository.save(hunting);
            }
        } else {
            throw new RuntimeException("The weight of the hunted fish is less than the average weight");
        }
    }
}
