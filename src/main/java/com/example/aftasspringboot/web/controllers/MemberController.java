package com.example.aftasspringboot.web.controllers;

import com.example.aftasspringboot.Services.MemberService;
import com.example.aftasspringboot.dtos.requests.MemberRequest;
import com.example.aftasspringboot.entities.Member;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@AllArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<Member> getCompetitions() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}")
    public Member getMemberById(@PathVariable Long memberId) {
        return memberService.getMemberById(memberId);
    }

    @PostMapping()
    public Member createMember(@RequestBody @Valid MemberRequest memberRequest) {
        return memberService.createMember(memberRequest);
    }

    @PutMapping("/{memberId}")
    public Member updateMember(@PathVariable Long memberId,@RequestBody @Valid MemberRequest memberRequest) {
        return memberService.updateMember(memberRequest, memberId);
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }

}
