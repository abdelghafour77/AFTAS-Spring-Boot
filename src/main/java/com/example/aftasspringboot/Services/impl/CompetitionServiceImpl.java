package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.CompetitionService;
import com.example.aftasspringboot.dtos.requests.CompetitionRequest;
import com.example.aftasspringboot.dtos.responses.CompetitionResponse;
import com.example.aftasspringboot.entities.Competition;
import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.entities.RankId;
import com.example.aftasspringboot.entities.Ranking;
import com.example.aftasspringboot.repositories.CompetitionRepository;
import com.example.aftasspringboot.repositories.MemberRepository;
import com.example.aftasspringboot.repositories.RankingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final MemberRepository memberRepository;
    private final RankingRepository rankingRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, MemberRepository memberRepository, RankingRepository rankingRepository) {
        this.competitionRepository = competitionRepository;
        this.memberRepository = memberRepository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        refreshStatus();
        return competitionRepository.findAll();
    }

    @Override
    public Page<CompetitionResponse> getAllPageableCompetitions(Pageable pageable) {
        refreshStatus();
        Page<Competition> competitions = competitionRepository.findAll(pageable);
        return competitions.map(CompetitionResponse::fromEntity);
    }


    @Override
    public Competition getCompetitionById(Long id) {
//        refreshStatus();
        if (competitionRepository.findById(id).isPresent())
            return competitionRepository.findById(id).get();
        else
            throw new RuntimeException("Competition not found");
    }

    @Override
    public Competition getCompetitionByCode(String code) {
        if (competitionRepository.findByCode(code) != null)
            return competitionRepository.findByCode(code);
        else
            throw new RuntimeException("Competition not found");
    }


    @Override
    public Competition createCompetition(CompetitionRequest competitionRequest) {

        if (competitionRequest.getDate().isBefore(java.time.LocalDate.now()))
            throw new RuntimeException("Date must be in the future");
        if (competitionRequest.getStartTime().isAfter(competitionRequest.getEndTime()))
            throw new RuntimeException("Start time must be before end time");
        if (competitionRequest.getDate().isBefore(java.time.LocalDate.now().plusDays(2)))
            throw new RuntimeException("Date must be within 2 days");
        else {
            String code = generateCode(competitionRequest.getLocation(), competitionRequest.getDate());
            if (competitionRepository.findByCode(code) != null)
                throw new RuntimeException("Competition with this code already exists");
            else {
                Competition competition = Competition.builder()
                        .code(code)
                        .date(competitionRequest.getDate())
                        .name(competitionRequest.getName())
                        .description(competitionRequest.getDescription())
                        .startTime(competitionRequest.getStartTime())
                        .endTime(competitionRequest.getEndTime())
                        .status("OPEN")
                        .numberOfParticipants(competitionRequest.getNumberOfParticipants())
                        .location(competitionRequest.getLocation())
                        .price(competitionRequest.getPrice())
                        .build();

                return competitionRepository.save(competition);
            }
        }
    }

    @Override
    public Competition updateCompetition(CompetitionRequest competition, Long competitionId) {

        if (competition.getDate().isBefore(java.time.LocalDate.now()))
            throw new RuntimeException("Date must be in the future");
        if (competition.getStartTime().isAfter(competition.getEndTime()))
            throw new RuntimeException("Start time must be before end time");
        if (competition.getDate().isBefore(java.time.LocalDate.now().plusDays(2)))
            throw new RuntimeException("Date must be within 2 days");
        else {
            Competition competition1 = competitionRepository.findById(competitionId).orElseThrow(() -> new RuntimeException("Competition not found"));
            competition1.setDate(competition.getDate());
            competition1.setName(competition.getName());
            competition1.setDescription(competition.getDescription());
            competition1.setStartTime(competition.getStartTime());
            competition1.setEndTime(competition.getEndTime());
            competition1.setNumberOfParticipants(competition.getNumberOfParticipants());
            competition1.setLocation(competition.getLocation());
            competition1.setPrice(competition.getPrice());
            return competitionRepository.save(competition1);
        }
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.findById(id).orElseThrow(() -> new RuntimeException("Competition not found"));
        competitionRepository.deleteById(id);
    }

    @Override
    public void results(Long id) {

    }


    @Override
    public Page<CompetitionResponse> searchCompetitions(String value, Pageable pageable) {
        return CompetitionResponse.fromEntities(competitionRepository.findByNameOrCodeOrLocationLike(value, pageable));

    }

    @Override
    public Page<CompetitionResponse> getCompetitionsByStatus(String status, Pageable pageable) {
       return   CompetitionResponse.fromEntities(competitionRepository.findByStatus(status, pageable));

    }

    @Override
    public Competition register(String code, Long memberId) {
        Competition competition = competitionRepository.findByCode(code);
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        if (competition == null)
            throw new RuntimeException("Competition not found");
        if (competition.getNumberOfParticipants() == competition.getRankings().size())
            throw new RuntimeException("Competition is full");
        if (competition.getRankings().stream().anyMatch(ranking -> ranking.getMember().getId().equals(memberId)))
            throw new RuntimeException("Member already registered");
        else {
            int rank = competition.getRankings().size() + 1;
            Ranking ranking = Ranking.builder()
                    .id(RankId.builder().competitionId(member.getId()).competitionId(competition.getId()).build())
                    .competition(competition)
                    .member(member)
                    .score(0)
                    .rank(rank)
                    .build();
            rankingRepository.save(ranking);
        }


        return getCompetitionByCode(code);
    }

    @Override
    public List<Member> getParticipants(String code) {

        // Get the competition by its code
        Competition competition = competitionRepository.findByCode(code);
        if (competition == null) {
            throw new RuntimeException("Competition not found");
        }

        // Get all the members who are registered in this competition
        List<Member> registeredMembers = competition.getRankings().stream()
                .map(Ranking::getMember)
                .map(member -> memberRepository.findById(member.getId()).get())
                .toList();

        // Get all the members
        List<Member> allMembers = memberRepository.findAll();

        // Filter out the members who are registered in the competition

        return allMembers.stream()
                .filter(member -> !registeredMembers.contains(member))
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> getParticipantsWithSearch(String code, String search) {

        // Get the competition by its code
        Competition competition = competitionRepository.findByCode(code);
        if (competition == null) {
            throw new RuntimeException("Competition not found");
        }

        // Get all the members who are registered in this competition
        List<Member> registeredMembers = competition.getRankings().stream()
                .map(Ranking::getMember)
                .map(member -> memberRepository.findById(member.getId()).get())
                .toList();

        // Get all the members
        List<Member> allMembers = memberRepository.findByCriteria(search);

        // Filter out the members who are registered in the competition

        return allMembers.stream()
                .filter(member -> !registeredMembers.contains(member))
                .collect(Collectors.toList());
    }

    public void refreshStatus() {
        List<Competition> competitions = competitionRepository.findAll();
        for (Competition competition : competitions
        ) {
            LocalDate date = competition.getDate();
            LocalTime startTime = competition.getStartTime();
            LocalTime endTime = competition.getEndTime();
            LocalDate now = LocalDate.now();
            LocalTime nowTime = LocalTime.now();
            if (date.isBefore(now)) {
                competition.setStatus("finished");
            } else if (date.isEqual(now)) {
                if (startTime.isBefore(nowTime) && endTime.isAfter(nowTime)) {
                    competition.setStatus("ongoing");
                } else if (startTime.isAfter(nowTime)) {
                    competition.setStatus("upcoming");
                } else {
                    competition.setStatus("finished");
                }
            } else {
                competition.setStatus("upcoming");
            }
            competitionRepository.save(competition);
        }
    }

    public static String generateCode(String location, LocalDate date) {
        String locationCode = location.substring(0, Math.min(location.length(), 3)).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }


}
