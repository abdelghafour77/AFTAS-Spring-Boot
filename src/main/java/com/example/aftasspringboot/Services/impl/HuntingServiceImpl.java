package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.HuntingService;
import com.example.aftasspringboot.dtos.requests.HuntingRequest;
import com.example.aftasspringboot.entities.*;
import com.example.aftasspringboot.handler.exception.costumExceptions.ValidationException;
import com.example.aftasspringboot.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final FishRepository fishRepository;
    private final MemberRepository memberRepository;
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;


    public HuntingServiceImpl(HuntingRepository huntingRepository, FishRepository fishRepository, MemberRepository memberRepository, CompetitionRepository competitionRepository, RankingRepository rankingRepository) {
        this.huntingRepository = huntingRepository;
        this.fishRepository = fishRepository;
        this.memberRepository = memberRepository;
        this.competitionRepository = competitionRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Hunting addHunting(HuntingRequest huntingRequest) {
        // Retrieve the average weight of the fish
        Fish fish = fishRepository.findById(huntingRequest.getFishId())
                .orElseThrow(() -> new  ValidationException("Fish not found"));

        Double avgWeight = fish.getAvgWeight();

        // Compare the weight of the hunted fish with the average weight
        if (huntingRequest.getWeightOfHuntedFish() > avgWeight) {
            // Check if a hunting record already exists for this fish
            Member member = memberRepository.findById(huntingRequest.getMemberId())
                    .orElseThrow(() -> new ValidationException("Member not found"));

            Competition competition = competitionRepository.findById(huntingRequest.getCompetitionId())
                    .orElseThrow(() -> new ValidationException("Competition not found"));

            Optional<Hunting> existingHunting = huntingRepository.findByCompetitionAndMemberAndFish(competition, member, fish);
            Ranking ranking = rankingRepository.findByMemberAndCompetition(member, competition)
                    .orElseThrow(() -> new ValidationException("Ranking not found"));
            if (existingHunting.isPresent()) {
                // If a hunting record exists, increment the numberOfFish
                Hunting existingHunt = existingHunting.get();
                existingHunt.setNumberOfFish(existingHunt.getNumberOfFish() + 1);
                ranking.setScore(fish.getLevel().getPoints() + ranking.getScore());
                rankingRepository.save(ranking);
                updateRanking(competition);
                return huntingRepository.save(existingHunt);

            } else {

                ranking.setScore(fish.getLevel().getPoints() + ranking.getScore());
                rankingRepository.save(ranking);
                // If a hunting record does not exist, create a new Hunting entity and set numberOfFish to 1
                Hunting hunting = Hunting.builder()
                        .fish(fish)
                        .member(member)
                        .competition(competition)
                        .numberOfFish(1)
                        .build();
                updateRanking(competition);
                return huntingRepository.save(hunting);
            }
        } else {
            throw new ValidationException("The weight of the hunted fish is less than the average weight");
        }
    }

    private void updateRanking( Competition competition) {
        // Get all the rankings associated with this competition
        List<Ranking> rankings = competition.getRankings();

        // Sort the rankings based on the score in descending order
        rankings.sort((r1, r2) -> r2.getScore().compareTo(r1.getScore()));

        // Assign the rank based on the position in the list
        for (int i = 0; i < rankings.size(); i++) {
            rankings.get(i).setRank(i + 1);
        }

        rankingRepository.saveAll(rankings);
    }
}
