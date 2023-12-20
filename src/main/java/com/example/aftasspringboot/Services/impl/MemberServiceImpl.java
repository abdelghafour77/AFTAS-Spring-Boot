package com.example.aftasspringboot.Services.impl;

import com.example.aftasspringboot.Services.MemberService;
import com.example.aftasspringboot.dtos.requests.MemberRequest;
import com.example.aftasspringboot.entities.Member;
import com.example.aftasspringboot.handler.exception.costumExceptions.ValidationException;
import com.example.aftasspringboot.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long id) {
        if(memberRepository.findById(id).isPresent())
            return memberRepository.findById(id).get();
        else
            throw new ValidationException("Member not found");
    }

    @Override
    public Member getMemberByCriteria(String criteria) {
        return null;
    }

    @Override
    public Member createMember(MemberRequest memberRequest) {
        if(memberRepository.findByIdentityNumber(memberRequest.getIdentityNumber()).isPresent())
            throw new ValidationException("Member already exists");
        else{
            Member member = Member.builder()
                    .firstName(memberRequest.getFirstName())
                    .lastName(memberRequest.getLastName())
                    .accessionDate(LocalDate.now())
                    .identityNumber(memberRequest.getIdentityNumber())
                    .identityDocumentType(memberRequest.getIdentityDocumentType())
                    .nationality(memberRequest.getNationality())
                    .build();
            return memberRepository.save(member);
        }

    }

    @Override
    public Member updateMember(MemberRequest member, Long memberId) {
        if(memberRepository.findById(memberId).isPresent()){
            Member member1=Member.builder()
                    .id(memberId)
                    .firstName(member.getFirstName())
                    .lastName(member.getLastName())
                    .accessionDate(member.getAccessionDate())
                    .identityNumber(member.getIdentityNumber())
                    .identityDocumentType(member.getIdentityDocumentType())
                    .nationality(member.getNationality())
                    .build();
            return memberRepository.save(member1);
        }
        else
            throw new ValidationException("Member not found");
    }

    @Override
    public void deleteMember(Long id) {
        if(memberRepository.findById(id).isPresent())
            memberRepository.deleteById(id);
        else
            throw new ValidationException("Member not found");

    }

    @Override
    public List<Member> searchMembers(String value) {
        return null;
    }
}
